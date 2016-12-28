package models;

import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

import values.IntValue;
import values.StringValue;

public class Piece extends JLabel{
	private int mWidth;
	private int mHeight;
	private String mBgPath;
	private String mClickedBgPath;
	protected boolean mVerticalMovable;
	protected boolean mHorizontalMovable;
	private MouseListener mMouseListener;
	private MouseMotionListener mMouseMotionListener;
	
	
	//构造一个位置为(positionX,positionY)、宽度为width、高度为height的棋子
	public Piece(int positionX,int positionY,int width,int height){
		//要保证长、宽不超过棋盘大小
		mWidth=width>IntValue.CHECKERBOARD_WIDTH?IntValue.CHECKERBOARD_WIDTH:width;
		mHeight=height>IntValue.CHECKERBOARD_HEIGHT?IntValue.CHECKERBOARD_HEIGHT:height;
		if(mWidth<1||mHeight<1){
			mHeight=1;
			mWidth=1;
		}
		//计算宽高，定位组件
		width=width*IntValue.UNIT_LEN;
		height=height*IntValue.UNIT_LEN;
		setBounds(positionX, positionY, width, height);
		
		//初始化背景
		mBgPath=StringValue.SHORT_PIECE_IMG;
		mClickedBgPath=StringValue.CLICKED_SHORT_PIECE_IMG;
		
		//初始化为可平行与垂直移动
		mHorizontalMovable=true;
		mVerticalMovable=true;
		
		//初始化当前监听器，由于一开始没有，故为null
		mMouseListener=null;
		mMouseMotionListener=null;
		//显示背景
		useBackground();
		//设置为可见
		setVisible(true);
	}
	
	public Piece(int width,int height){
		this(0,0,width,height);
	}
	
	public int getPieceWidth() {
		return mWidth;
	}

	public int getPieceHeight() {
		return mHeight;
	}
	
	public boolean isVerticalMovable() {
		return mVerticalMovable;
	}

	public boolean isHorizontalMovable() {
		return mHorizontalMovable;
	}
	
	public void setBackground(String bgPath) {
		mBgPath=bgPath;
	}
	
	public void useBackground() {
		setBg(mBgPath);
	}
	
	public void setClickedBackground(String bgPath){
		mClickedBgPath=bgPath;
	}
	
	public void useClickedBackground(){
		setBg(mClickedBgPath);
	}
	
	private void setBg(String bgPath) {
		ImageIcon imageIcon=new ImageIcon(bgPath);
		Image image=imageIcon.getImage();
		image=image.getScaledInstance(getWidth(),getHeight(), Image.SCALE_FAST);
		setIcon(new ImageIcon(image));
	}
	
	public void movePiece(int horizontal, int vertical) {
		//水平移动
		int positionX=getX()+horizontal;
		if(positionX<0)
			positionX=0;
		
		//垂直移动
		int positionY=getY()+vertical;
		if(positionY<0)
			positionY=0;
		
		super.setLocation(positionX, positionY);
	}
	
	
	public void setMouseListener(MouseListener mouseListener) {
		removeMouseListener(mMouseListener);
		addMouseListener(mouseListener);
		
	}
	
	public void setMouseMotionListener(MouseMotionListener motionListener) {
		removeMouseMotionListener(mMouseMotionListener);
		addMouseMotionListener(motionListener);
	}
	
	static public void main(String[] args){
		JFrame frame=new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel(null);
		Piece myPiece=new Piece(100, 200, 2, 1);
		panel.add(myPiece);
		frame.getContentPane().add(panel);
		//myPiece.move(0, 200);
		frame.setVisible(true);
	}
}
