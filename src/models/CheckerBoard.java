package models;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.management.MBeanAttributeInfo;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.LevelModel.PieceInformation;
import values.IntValue;
import values.StringValue;

public class CheckerBoard extends JLabel{
	//棋子列表
	private ArrayList<PieceHolder> mPieces;
	//棋子数量
	private int mNumber;
	//棋盘可用情况
	private boolean[][] mSpace;
	
	public CheckerBoard() {
		mPieces=new ArrayList<PieceHolder>();
		mNumber=0;
		mSpace=new boolean[IntValue.CHECKERBOARD_HEIGHT][IntValue.CHECKERBOARD_WIDTH];
		for(int i=0;i<IntValue.CHECKERBOARD_HEIGHT;i++)
			for(int j=0;j<IntValue.CHECKERBOARD_HEIGHT;j++)
				mSpace[i][j]=true;
		

		int width=IntValue.UNIT_LEN*IntValue.CHECKERBOARD_WIDTH;
		int height=IntValue.UNIT_LEN*IntValue.CHECKERBOARD_HEIGHT;
		//设置背景
		ImageIcon imageIcon=new ImageIcon(StringValue.CHECKERBOARD_IMG);
		Image image=imageIcon.getImage();
		image=image.getScaledInstance(width,height, Image.SCALE_FAST);
		setIcon(new ImageIcon(image));
		
		setBounds(0, 0, width, height);
	}
	
	public CheckerBoard(PieceInformation[] pieces){
		this();
		
		int len=pieces.length;
		for(int i=0;i<len;i++){
			addPiece(new Piece(pieces[i].getWidth(),pieces[i].getHeight()),
					pieces[i].getX(), pieces[i].getY());
		}
	}
	
	//添加一个棋子在位置(x,y)处，返回添加成功后的棋子序号。
	public int addPiece(Piece piece,int x,int y) {
		boolean success=isAddable(piece, x, y);
		
		if(success){
			mPieces.add(new PieceHolder(x, y, piece));
			setSpace(x, y, piece.getPieceWidth(), piece.getPieceHeight(), false);
			putPiece(piece, x, y);
			mNumber++;
			return mNumber-1;
		}
		else
			return -1;
	}
	
	//根据相对位置(x,y)把棋子piece放到棋盘上。
	private void putPiece(Piece piece,int x,int y) {
		x=(int)getLocation().getX()+x*IntValue.UNIT_LEN;
		y=(int)getLocation().getY()+y*IntValue.UNIT_LEN;
		piece.setLocation(x, y);
		add(piece);
	}
	
	//将棋子放到(x,y)位置上，有回归动画
	public void setPiece(int pieceNum,int x,int y){
		PieceHolder pieceHolder=getPieceHolder(pieceNum);

		//存下原始位置
		int oldX=pieceHolder.getX();
		int oldY=pieceHolder.getY();
		Piece piece=pieceHolder.getPiece();
		
		if(oldX!=x||oldY!=y){
			setSpace(oldX, oldY, piece.getPieceWidth(), piece.getPieceHeight(), true);
			if(isAddable(piece, x, y)){
				pieceHolder.setX(x);
				pieceHolder.setY(y);
				setSpace(x, y, piece.getPieceWidth(), piece.getPieceHeight(), false);
			}
			else
				setSpace(oldX, oldY, piece.getPieceWidth(), piece.getPieceHeight(), false);
		}
		
		PieceReturn(piece, x, y);
	}
	
	//回归动画
	private void PieceReturn(Piece piece,int x,int y){
		//目的坐标
		x=(int)getLocation().getX()+x*IntValue.UNIT_LEN;
		y=(int)getLocation().getY()+y*IntValue.UNIT_LEN;
		
		//当前坐标
		int nowX=piece.getX();
		int nowY=piece.getY();
		
		//速度
		int velocityX=x>nowX?IntValue.VELOCITY:(-IntValue.VELOCITY);
		int velocityY=y>nowY?IntValue.VELOCITY:(-IntValue.VELOCITY);
		
		//重绘次数
		int countX=(x-nowX)/velocityX;
		int countY=(y-nowY)/velocityY;
		int count=countX>countY?countX:countY;
		
		//重绘
		for(int i=0;i<count;i++){
			if(i<countX)
				nowX=nowX+velocityX;
			if(i<countY)
				nowY=nowY+velocityY;
			piece.setLocation(nowX, nowY);
			repaint();
			
			try {
				Thread.sleep(IntValue.SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		piece.setLocation(x, y);
	}
	
	//判断是否可以添加棋子
	private boolean isAddable(Piece piece,int x,int y){
		boolean isAddable=true;
		
		if(y<0||(y+piece.getPieceHeight())>IntValue.CHECKERBOARD_HEIGHT||
				x<0||(x+piece.getPieceWidth()>IntValue.CHECKERBOARD_WIDTH))
			isAddable=false;
		else{
			for(int i=0;i<piece.getPieceHeight();i++)
				if(!mSpace[y+i][x])
					isAddable=false;
			
			for(int j=0;j<piece.getPieceWidth();j++)
				if(!mSpace[y][x+j])
					isAddable=false;
		}
		
		return isAddable;
	}
	
	//设置mSpace，对应矩形区域设为对应
	private void setSpace(int x,int y,int width,int height,boolean b) {
		for(int i=0;i<height;i++)
			for(int j=0;j<width;j++)
				mSpace[y+i][x+j]=b;


		//调试
		/*for(int i=0;i<IntValue.CHECKERBOARD_HEIGHT;i++)
			{
			for(int j=0;j<IntValue.CHECKERBOARD_WIDTH;j++)
				System.out.print(mSpace[i][j]+"\t");
			System.out.println();
			}
		System.out.println("-----------------------------------");*/
	}
	
	
	//判断编号为pieceNum的棋子是否可以移动到(x,y)上
	public boolean isMovable(int pieceNum,int x,int y){
		
		boolean isMovable=false;
		PieceHolder pieceHolder=getPieceHolder(pieceNum);

		//存下原始位置
		int oldX=pieceHolder.getX();
		int oldY=pieceHolder.getY();
		Piece piece=pieceHolder.getPiece();
		
		if(oldX!=x||oldY!=y){
			setSpace(oldX, oldY, piece.getPieceWidth(), piece.getPieceHeight(), true);
			if(isAddable(piece, x, y))
				isMovable=true;
			setSpace(oldX, oldY, piece.getPieceWidth(), piece.getPieceHeight(), false);
			
		}else
			isMovable=true;
		
		return isMovable;
	}
	
	//获得对应编号的棋子
	
	public Piece getPiece(int position){
		PieceHolder pieceHolder=mPieces.get(position);
		return pieceHolder.getPiece();
	}
	
	//获得对应编号的pieceHolder
	private PieceHolder getPieceHolder(int position){
		return mPieces.get(position);
	}
	
	//棋子holder，存储棋子和它的位置
	private class PieceHolder{
		private int mX;
		private int mY;
		private Piece mPiece;
		
		public PieceHolder(int x,int y,Piece piece) {
			mX=x;
			mY=y;
			mPiece=piece;
		}
		
		public Piece getPiece(){
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
	
	//棋子监听器
	public class PieceDragListener implements MouseListener,MouseMotionListener{

		private CheckerBoard mCheckerBoard;
		private int mPieceNum;
		private int mX;
		private int mY;
		private int mDX;
		private int mDY;
		private Piece mPiece;
		//用于控制拖动
		private int mMouseStartX;
		private int mMouseStartY;
		//记录初始位置
		private int mStartPositionX;
		private int mStartPositionY;
		
		//标志，0为可以移动状态，1为不可以正方向移动，-1为不可以负方向移动
		private int mFlag;
		
		public PieceDragListener(int pieceNum) {
			mCheckerBoard=CheckerBoard.this;
			mPieceNum=pieceNum;
			PieceHolder pieceHolder=mCheckerBoard.getPieceHolder(pieceNum);
			mX=pieceHolder.getX();
			mY=pieceHolder.getY();
			mPiece=pieceHolder.getPiece();
			mFlag=0;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			mMouseStartX=e.getX();
			mMouseStartY=e.getY();
			mStartPositionX=mPiece.getX();
			mStartPositionY=mPiece.getY();
			mDX=0;
			mDY=0;
			mPiece.useClickedBackground();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mPiece.useBackground();
			mX+=mDX;
			mY+=mDY;
			mFlag=0;
			mCheckerBoard.setPiece(mPieceNum, mX, mY);
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if(mPiece.isHorizontalMovable()){
				//棋子鼠标坐标位移
				int mouseDX=e.getX()-mMouseStartX;
				//棋子坐标位移
				int positionDX=mPiece.getX()-mStartPositionX+mouseDX;
				//目标棋盘位置位移
				int dX=positionDX/IntValue.UNIT_LEN;
				if(positionDX>0)
					dX++;
				else if(positionDX<0)
					dX--;
				
				if((dX>0&&mFlag!=1)||(dX<0&&mFlag!=-1)){
					if(mCheckerBoard.isMovable(mPieceNum, mX+dX, mY)){
						mDX=dX;
						mPiece.movePiece(mouseDX, 0);
						mFlag=0;
					}else{
						if(dX>0)
							mFlag=1;
						else if(dX<0)
							mFlag=-1;
					}
				}
			}
			
			if(mPiece.isVerticalMovable()){
				//棋子鼠标坐标位移
				int mouseDY=e.getY()-mMouseStartY;
				//棋子坐标位移
				int positionDY=mPiece.getY()-mStartPositionY+mouseDY;
				//目标棋盘位置位移
				int dY=positionDY/IntValue.UNIT_LEN;
				if(positionDY>0)
					dY++;
				else if(positionDY<0)
					dY--;
				
				if((dY>0&&mFlag!=1)||(dY<0&&mFlag!=-1)){
					if(mCheckerBoard.isMovable(mPieceNum, mX, mY+dY)){
						mDY=dY;
						mPiece.movePiece(0, mouseDY);
						mFlag=0;
					}else{
						if(dY>0)
							mFlag=1;
						else if(dY<0)
							mFlag=-1;
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

	//测试
		static public void main(String[] args){
			JFrame frame=new JFrame();
			frame.setSize(600, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel=new JPanel(null);
			
			CheckerBoard model=new CheckerBoard();
			Piece myPiece=new HorizontalLongPiece(0, 0);
			Piece myPiece2=new VerticalShortPiece(0, 0);
			int piece1Num=model.addPiece(myPiece, 0, 0);
			int piece2Num=model.addPiece(myPiece2, 0, 1);
			
			panel.add(model);
			frame.getContentPane().add(panel);
			frame.setVisible(true);
			
			//myPiece.movePiece(147, 0);
			//model.PieceReturn(myPiece, 2, 2);
			
			PieceDragListener pieceDragListener=model.new PieceDragListener(piece1Num);
			myPiece.setMouseListener(pieceDragListener);
			myPiece.setMouseMotionListener(pieceDragListener);
			

			PieceDragListener pieceDragListener2=model.new PieceDragListener(piece2Num);
			myPiece2.setMouseListener(pieceDragListener2);
			myPiece2.setMouseMotionListener(pieceDragListener2);
			
			
		}
}
