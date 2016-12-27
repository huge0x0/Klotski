package models;

import javax.swing.JFrame;
import javax.swing.JPanel;

import values.IntValue;
import views.PieceView;

public class Piece {
	private int mPositionX;
	private int mPositionY;
	private int mWidth;
	private int mHeight;
	private boolean mVisible;
	
	//棋子的样式
	private PieceView mPieceView;
	
	//默认构造函数 
	public Piece() {
		mPositionX=0;
		mPositionY=0;
		mWidth=0;
		mHeight=0;
		
		mPieceView=new PieceView(mPositionX,mPositionY,mWidth,mHeight);
		setVisible(false);
	}
	
	//构造一个位置为(positionX,positionY)、宽度为width、高度为height的棋子
	public Piece(int positionX,int positionY,int width,int height){
		mPositionX=positionX<0?0:positionX;
		mPositionY=positionY<0?0:positionY;
		
		//要保证长、宽不超过棋盘大小
		mWidth=width>IntValue.CHECKERBOARD_WIDTH?IntValue.CHECKERBOARD_WIDTH:width;
		mHeight=height>IntValue.CHECKERBOARD_HEIGHT?IntValue.CHECKERBOARD_HEIGHT:height;
		if(mWidth<1||mHeight<1){
			mHeight=1;
			mWidth=1;
		}

		mPieceView=new PieceView(mPositionX,mPositionY,mWidth,mHeight);
		setVisible(true);
	}
	
	public Piece(int width,int height){
		this(0,0,width,height);
	}
	
	//获得坐标与长、宽
	public int getPositionX() {
		return mPositionX;
	}

	public int getPositionY() {
		return mPositionY;
	}

	public int getWidth() {
		return mWidth;
	}

	public int getHeight() {
		return mHeight;
	}

	public boolean isVisible() {
		return mVisible;
	}
	
	public PieceView getPieceView() {
		return mPieceView;
	}
	
	public void move(int horizontal, int vertical) {
		//水平移动
		mPositionX+=horizontal;
		if(mPositionX<0)
			mPositionX=0;
		
		//垂直移动
		mPositionY+=vertical;
		if(mPositionY<0)
			mPositionY=0;
		
		mPieceView.setLocation(mPositionX, mPositionY);
	}
	
	//设置是否可见
	public void setVisible(boolean isVisible) {
		mVisible=isVisible;	
		mPieceView.setVisible(mVisible);
	}
	
	static public void main(String[] args){
		JFrame frame=new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel(null);
		Piece myPiece=new Piece(100, 100, 1, 1);
		panel.add(myPiece.getPieceView());
		frame.getContentPane().add(panel);
		//myPiece.move(0, 200);
		frame.setVisible(true);
	}
}
