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
	
	
	//����һ��λ��Ϊ(positionX,positionY)�����Ϊwidth���߶�Ϊheight������
	public Piece(int positionX,int positionY,int width,int height){
		//Ҫ��֤�������������̴�С
		mWidth=width>IntValue.CHECKERBOARD_WIDTH?IntValue.CHECKERBOARD_WIDTH:width;
		mHeight=height>IntValue.CHECKERBOARD_HEIGHT?IntValue.CHECKERBOARD_HEIGHT:height;
		if(mWidth<1||mHeight<1){
			mHeight=1;
			mWidth=1;
		}
		//�����ߣ���λ���
		width=width*IntValue.UNIT_LEN;
		height=height*IntValue.UNIT_LEN;
		setBounds(positionX, positionY, width, height);
		
		//��ʼ������
		mBgPath=StringValue.SHORT_PIECE_IMG;
		mClickedBgPath=StringValue.CLICKED_SHORT_PIECE_IMG;
		
		//��ʼ��Ϊ��ƽ���봹ֱ�ƶ�
		mHorizontalMovable=true;
		mVerticalMovable=true;
		
		//��ʼ����ǰ������������һ��ʼû�У���Ϊnull
		mMouseListener=null;
		mMouseMotionListener=null;
		//��ʾ����
		useBackground();
		//����Ϊ�ɼ�
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
		//ˮƽ�ƶ�
		int positionX=getX()+horizontal;
		if(positionX<0)
			positionX=0;
		
		//��ֱ�ƶ�
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
