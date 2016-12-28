package models;

import javax.swing.JFrame;
import javax.swing.JPanel;

import values.StringValue;

public class VerticalLongPiece extends Piece {
	
	public VerticalLongPiece(int positionX,int positionY) {
		super(positionX,positionY,1,3);
		setBackground(StringValue.LONG_PIECE_IMG);
		setClickedBackground(StringValue.CLICKED_LONG_PIECE_IMG);
		useBackground();
		mHorizontalMovable=false;
	}
	
	public void moveUp(int offset) {
		if(offset>0)
			super.setLocation(getX(), offset);
	}
	
	public void moveDown(int offset) {
		if(offset>0)
			super.setLocation(getX(), -offset);
	}
	
	public void setHorizontalMovable(boolean isMovable) {
		mHorizontalMovable = isMovable;
	}
	
	@Override
	public void movePiece(int horizontal,int vertical){
		if(mHorizontalMovable)
			super.movePiece(horizontal,vertical);
		else
			super.movePiece(0, vertical);
	}
	
	static public void main(String[] args){
		JFrame frame=new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel(null);
		
		VerticalLongPiece myPiece=new VerticalLongPiece(200, 100);
		panel.add(myPiece);
		frame.getContentPane().add(panel);
		//myPiece.movePiece(0, 200);
		frame.setVisible(true);
	}
}
