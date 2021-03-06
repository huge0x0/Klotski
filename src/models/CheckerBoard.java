package models;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Stack;

import javax.management.MBeanAttributeInfo;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.Play;
import models.LevelModel.PieceInformation;
import values.IntValue;
import values.StringValue;

public class CheckerBoard extends JLabel {
	// 棋子数组
	private ArrayList<PieceHolder> mPieces;
	// 棋子数量
	private int mNumber;
	// 棋盘可用空间
	private boolean[][] mSpace;
	// 棋盘出口
	private int mTargetY;
	// 主棋子序号
	private int mMainPieceNum;
	//步骤记录
	private Stack<PieceRecord> mRecords;

	public CheckerBoard() {
		mPieces = new ArrayList<PieceHolder>();
		mRecords=new Stack<>();
		mNumber = 0;
		mSpace = new boolean[IntValue.CHECKERBOARD_HEIGHT][IntValue.CHECKERBOARD_WIDTH];
		for (int i = 0; i < IntValue.CHECKERBOARD_HEIGHT; i++)
			for (int j = 0; j < IntValue.CHECKERBOARD_HEIGHT; j++)
				mSpace[i][j] = true;
		mTargetY = -1;
		mMainPieceNum = -1;

		int width = IntValue.UNIT_LEN * IntValue.CHECKERBOARD_WIDTH;
		int height = IntValue.UNIT_LEN * IntValue.CHECKERBOARD_HEIGHT;
		// 设置背景
		ImageIcon imageIcon = new ImageIcon(StringValue.CHECKERBOARD_IMG);
		Image image = imageIcon.getImage();
		image = image.getScaledInstance(width, height, Image.SCALE_FAST);
		setIcon(new ImageIcon(image));

		setBounds(0, 0, width, height);
	}

	public CheckerBoard(PieceInformation[] pieces) {
		this();

		int len = pieces.length;
		for (int i = 0; i < len; i++) {
			int kind = pieces[i].getkind();
			if (kind == PieceInformation.MAIN)
				addMainPiece(new MainPiece(0, 0), pieces[i].getX(), pieces[i].getY());
			else {
				Piece piece = null;
				switch (kind) {
				case PieceInformation.HORIZONTAL_LONG:
					piece = new HorizontalLongPiece(0, 0);
					break;
				case PieceInformation.HORIZONTAL_SHORT:
					piece = new HorizontalShortPiece(0, 0);
					break;
				case PieceInformation.VERTICAL_SHORT:
					piece = new VerticalShortPiece(0, 0);
					break;
				case PieceInformation.VERTICAL_LONG:
					piece = new VerticalLongPiece(0, 0);
					break;
				}
				addNormalPiece(piece, pieces[i].getX(), pieces[i].getY());
			}
		}
	}

	// 添加一枚棋子，返回其编号，添加失败返回-1
	protected int addPiece(Piece piece, int x, int y) {
		boolean success = isAddable(piece, x, y);

		if (success) {
			mPieces.add(new PieceHolder(x, y, piece));
			setSpace(x, y, piece.getPieceWidth(), piece.getPieceHeight(), false);
			putPiece(piece, x, y);
			mNumber++;
			return mNumber - 1;
		} else
			return -1;
	}

	// 添加主棋子
	protected int addMainPiece(MainPiece mainPiece, int x, int y) {
		if (mMainPieceNum != -1)
			return mMainPieceNum;

		int num = addPiece(mainPiece, x, y);
		if (num != -1) {
			mTargetY = y;
			MainPieceListener mainPieceListener = new MainPieceListener(num);
			mainPiece.setMouseListener(mainPieceListener);
			mainPiece.setMouseMotionListener(mainPieceListener);
		}
		return num;
	}

	// 添加普通棋子
	public int addNormalPiece(Piece piece, int x, int y) {
		int num = addPiece(piece, x, y);
		if (num != -1) {
			PieceDragListener pieceDragListener = new PieceDragListener(num);
			piece.setMouseListener(pieceDragListener);
			piece.setMouseMotionListener(pieceDragListener);
		}
		return num;
	}

	// 把一个棋子放到棋盘上
	private void putPiece(Piece piece, int x, int y) {
		x = x * IntValue.UNIT_LEN;
		y = y * IntValue.UNIT_LEN;
		piece.setLocation(x, y);
		add(piece);
	}
	
	public void back(){
		PieceRecord pieceRecord=null;
		if(!mRecords.isEmpty())
			pieceRecord=mRecords.pop();
		if(pieceRecord!=null){
			int pieceNum=pieceRecord.num;
			int x=pieceRecord.x;
			int y=pieceRecord.y;
			
			PieceHolder pieceHolder = getPieceHolder(pieceNum);
			
			// 获得原来坐标
			int oldX = pieceHolder.getX();
			int oldY = pieceHolder.getY();
			Piece piece = pieceHolder.getPiece();
			
			if (oldX != x || oldY != y) {
				setSpace(oldX, oldY, piece.getPieceWidth(), piece.getPieceHeight(), true);
				if (isAddable(piece, x, y)) {
					pieceHolder.setX(x);
					pieceHolder.setY(y);
					setSpace(x, y, piece.getPieceWidth(), piece.getPieceHeight(), false);
				} else
					setSpace(oldX, oldY, piece.getPieceWidth(), piece.getPieceHeight(), false);
			}
	
			PieceReturn(piece, x, y);
		}
	}

	// 将对应编号棋子放到(x,y)处，有回归动画
	public void setPiece(int pieceNum, int x, int y) {
		PieceHolder pieceHolder = getPieceHolder(pieceNum);
		
		// 获得原来坐标
		int oldX = pieceHolder.getX();
		int oldY = pieceHolder.getY();
		Piece piece = pieceHolder.getPiece();
		
		if (oldX != x || oldY != y) {
			setSpace(oldX, oldY, piece.getPieceWidth(), piece.getPieceHeight(), true);
			if (isAddable(piece, x, y)) {
				mRecords.push(new PieceRecord(pieceNum, oldX, oldY));
				pieceHolder.setX(x);
				pieceHolder.setY(y);
				setSpace(x, y, piece.getPieceWidth(), piece.getPieceHeight(), false);
			} else
				setSpace(oldX, oldY, piece.getPieceWidth(), piece.getPieceHeight(), true);
		}
		PieceReturn(piece, x, y);
	}

	// 回归动画
	private void PieceReturn(Piece piece, int x, int y) {
		// 目标位置
		x = x * IntValue.UNIT_LEN;
		y = y * IntValue.UNIT_LEN;
		/*
		// 当前位置
		int nowX = piece.getX();
		int nowY = piece.getY();

		// 速度
		int velocityX = x > nowX ? IntValue.VELOCITY : (-IntValue.VELOCITY);
		int velocityY = y > nowY ? IntValue.VELOCITY : (-IntValue.VELOCITY);

		// 重绘次数
		int countX = (x - nowX) / velocityX;
		int countY = (y - nowY) / velocityY;
		int count = countX > countY ? countX : countY;

		// 重绘
		for (int i = 0; i < count; i++) {
			if (i < countX)
				nowX = nowX + velocityX;
			if (i < countY)
				nowY = nowY + velocityY;
			piece.setLocation(nowX, nowY);

			try {
				Thread.sleep(IntValue.SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		
		piece.setLocation(x, y);
	}

	// 是否可以添加
	private boolean isAddable(Piece piece, int x, int y) {
		boolean isAddable = true;

		if (y < 0 || (y + piece.getPieceHeight()) > IntValue.CHECKERBOARD_HEIGHT || x < 0
				|| (x + piece.getPieceWidth() > IntValue.CHECKERBOARD_WIDTH))
			isAddable = false;
		else {
			for (int i = 0; i < piece.getPieceHeight(); i++)
				if (!mSpace[y + i][x])
					isAddable = false;

			for (int j = 0; j < piece.getPieceWidth(); j++)
				if (!mSpace[y][x + j])
					isAddable = false;
		}

		return isAddable;
	}

	// 设置对应矩形区域为对应值b
	private void setSpace(int x, int y, int width, int height, boolean b) {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				mSpace[y + i][x + j] = b;
	}

	// 对应编号棋子是否可以移动到目标地点
	public boolean isMovable(int pieceNum, int x, int y) {

		boolean isMovable = false;
		PieceHolder pieceHolder = getPieceHolder(pieceNum);

		// 获得原始位置
		int oldX = pieceHolder.getX();
		int oldY = pieceHolder.getY();
		Piece piece = pieceHolder.getPiece();

		if (oldX != x || oldY != y) {
			setSpace(oldX, oldY, piece.getPieceWidth(), piece.getPieceHeight(), true);
			if (isAddable(piece, x, y))
				isMovable = true;
			setSpace(oldX, oldY, piece.getPieceWidth(), piece.getPieceHeight(), false);

		} else
			isMovable = true;

		return isMovable;
	}

	// 获得对应编号的棋子
	public Piece getPiece(int position) {
		PieceHolder pieceHolder = mPieces.get(position);
		return pieceHolder.getPiece();
	}

	// 获得对应编号的pieceHolder
	private PieceHolder getPieceHolder(int position) {
		return mPieces.get(position);
	}

	// 保存棋子、棋子位置
	private class PieceHolder {
		private int mX;
		private int mY;
		private Piece mPiece;

		public PieceHolder(int x, int y, Piece piece) {
			mX = x;
			mY = y;
			mPiece = piece;
		}

		public Piece getPiece() {
			return mPiece;
		}

		public int getX() {
			return mX;
		}

		public int getY() {
			return mY;
		}

		public void setX(int x) {
			mX = x;
		}

		public void setY(int y) {
			mY = y;
		}
	}

	private class PieceRecord{
		int x;
		int y;
		int num;
		public PieceRecord(int num,int x,int y) {
			this.x=x;
			this.y=y;
			this.num=num;
		}
	}

	// 普通棋子监听器
	private class PieceDragListener implements MouseListener, MouseMotionListener {

		protected CheckerBoard mCheckerBoard;
		protected int mPieceNum;
		protected int mX;
		protected int mY;
		protected Piece mPiece;
		private int mDX;
		private int mDY;
		// 鼠标相对棋子起始位置
		private int mMouseStartX;
		private int mMouseStartY;
		// 鼠标相对棋盘起始位置
		private int mStartPositionX;
		private int mStartPositionY;

		// 标志，-1表示不能负方向移动，1表示不能正方向移动，0表示可以移动
		private int mFlag;

		public PieceDragListener(int pieceNum) {
			mCheckerBoard = CheckerBoard.this;
			mPieceNum = pieceNum;
			PieceHolder pieceHolder = mCheckerBoard.getPieceHolder(pieceNum);
			mX = pieceHolder.getX();
			mY = pieceHolder.getY();
			mPiece = pieceHolder.getPiece();
			mFlag = 0;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			mMouseStartX = e.getX();
			mMouseStartY = e.getY();
			mStartPositionX = mPiece.getX();
			mStartPositionY = mPiece.getY();
			mDX = 0;
			mDY = 0;
			mPiece.useClickedBackground();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mPiece.useBackground();
			mX += mDX;
			mY += mDY;
			mFlag = 0;
			mCheckerBoard.setPiece(mPieceNum, mX, mY);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (mPiece.isHorizontalMovable()) {
				// 鼠标相对棋子的拖动距离
				int mouseDX = e.getX() - mMouseStartX;
				// 鼠标相对棋盘的拖动距离
				int positionDX = mPiece.getX() - mStartPositionX + mouseDX;
				// 鼠标在棋盘上走的格数
				int dX = positionDX / IntValue.UNIT_LEN;
				if (positionDX > 0)
					dX++;
				else if (positionDX < 0)
					dX--;

				if ((dX > 0 && mFlag != 1) || (dX < 0 && mFlag != -1)) {
					if (mCheckerBoard.isMovable(mPieceNum, mX + dX, mY)) {
						mDX = dX;
						mPiece.movePiece(mouseDX, 0);
						mFlag = 0;
					} else {
						if (dX > 0)
							mFlag = 1;
						else if (dX < 0)
							mFlag = -1;
					}
				}
			}

			if (mPiece.isVerticalMovable()) {
				// 如上
				int mouseDY = e.getY() - mMouseStartY;
				int positionDY = mPiece.getY() - mStartPositionY + mouseDY;
				int dY = positionDY / IntValue.UNIT_LEN;
				if (positionDY > 0)
					dY++;
				else if (positionDY < 0)
					dY--;

				if ((dY > 0 && mFlag != 1) || (dY < 0 && mFlag != -1)) {
					if (mCheckerBoard.isMovable(mPieceNum, mX, mY + dY)) {
						mDY = dY;
						mPiece.movePiece(0, mouseDY);
						mFlag = 0;
					} else {
						if (dY > 0)
							mFlag = 1;
						else if (dY < 0)
							mFlag = -1;
					}
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// 主棋子监听器
	private class MainPieceListener extends PieceDragListener {

		public MainPieceListener(int pieceNum) {
			super(pieceNum);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			if (mX == IntValue.CHECKERBOARD_WIDTH - mPiece.getPieceWidth() && mY == mCheckerBoard.mTargetY) {
				PieceReturn(mPiece, IntValue.CHECKERBOARD_WIDTH-1, mY);
				Play.getController().succeedPlay();
			}
		}

	}

	// 测试
	static public void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(null);

		CheckerBoard model = new CheckerBoard();
		Piece myPiece = new HorizontalLongPiece(0, 0);
		Piece myPiece2 = new VerticalShortPiece(0, 0);
		MainPiece mainPiece = new MainPiece(0, 0);
		int piece2Num = model.addNormalPiece(myPiece2, 0, 1);
		int piece1Num = model.addNormalPiece(myPiece, 1, 0);
		int mainPieceNum = model.addMainPiece(mainPiece, 0, 3);

		/*
		 * LevelModel levelModel=new LevelModel(); PieceInformation[]
		 * pieceInformations=new LevelModel.PieceInformation[2];
		 * pieceInformations[0]=levelModel.new PieceInformation(3, 0,
		 * PieceInformation.VERTICAL_LONG); pieceInformations[1]=levelModel.new
		 * PieceInformation(0, 1, PieceInformation.MAIN);
		 * 
		 * CheckerBoard model=new CheckerBoard(pieceInformations);
		 */
		panel.add(model);
		frame.getContentPane().add(panel);
		frame.setVisible(true);

		// myPiece.useClickedBackground();

		Piece myPiece3 = new HorizontalLongPiece(0, 0);
		int piece1Num3 = model.addNormalPiece(myPiece3, 0, 5);
	}
}
