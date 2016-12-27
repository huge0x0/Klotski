package models;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.LevelModel.PieceInformation;
import values.IntValue;
import views.CheckerBoardView;
import views.PieceView;

public class CheckerBoard {
	//棋子列表
	private ArrayList<PieceHolder> mPieces;
	//棋子数量
	private int mNumber;
	//棋盘可用情况
	private boolean[][] mSpace;
	//view
	private CheckerBoardView mCheckerBoardView;
	
	public CheckerBoard() {
		mPieces=new ArrayList<PieceHolder>();
		mNumber=0;
		mSpace=new boolean[IntValue.CHECKERBOARD_HEIGHT][IntValue.CHECKERBOARD_WIDTH];
		for(int i=0;i<IntValue.CHECKERBOARD_HEIGHT;i++)
			for(int j=0;j<IntValue.CHECKERBOARD_HEIGHT;j++)
				mSpace[i][j]=true;
		mCheckerBoardView=new CheckerBoardView();
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
		boolean success=isAvailable(piece, x, y);
		
		if(success){
			mPieces.add(new PieceHolder(x, y, piece));
			for(int i=y;i<piece.getHeight();i++)
				for(int j=x;j<piece.getWidth();j++)
					mSpace[i][j]=false;
			mCheckerBoardView.addPiece(piece.getPieceView(), x, y);
			mNumber++;
			return mNumber-1;
		}
		else
			return -1;
	}
	
	public boolean isAvailable(Piece piece,int x,int y){
		boolean isAvailable=true;
		
		if(y>=0&&(y+piece.getHeight())<=IntValue.CHECKERBOARD_HEIGHT){
			for(int i=y;i<piece.getHeight();i++)
				if(!mSpace[i][x])
					isAvailable=false;
		}else
			isAvailable=false;
		
		if(x>=0&&(x+piece.getWidth()<=IntValue.CHECKERBOARD_WIDTH)){
			for(int j=x;j<piece.getWidth();j++)
				if(!mSpace[y][j])
					isAvailable=false;
		}else
			isAvailable=false;
		
		return isAvailable;
	}
	
	public Piece getPiece(int position){
		PieceHolder pieceHolder=mPieces.get(position);
		return pieceHolder.getPiece();
	}
	
	public CheckerBoardView getView() {
		return mCheckerBoardView;
	}
	
	public class PieceHolder{
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
	}
	
	//测试
		static public void main(String[] args){
			JFrame frame=new JFrame();
			frame.setSize(600, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel=new JPanel(null);
			CheckerBoard model=new CheckerBoard();
			Piece myPiece=new Piece(0, 0, 1, 3);
			Piece myPiece2=new Piece(0, 0, 3, 1);
			model.addPiece(myPiece, 0, 0);
			model.addPiece(myPiece2, 1, 1);
			CheckerBoardView view=model.getView();
			
			panel.add(view);
			frame.getContentPane().add(panel);
			frame.setVisible(true);
		}
}
