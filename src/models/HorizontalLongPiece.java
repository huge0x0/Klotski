package models;

import values.StringValue;

public class HorizontalLongPiece extends Piece {
	
	public HorizontalLongPiece(int positionX,int positionY) {
		super(positionX,positionY,3,1);
		setBackground(StringValue.LONG_PIECE_IMG);
		setClickedBackground(StringValue.CLICKED_LONG_PIECE_IMG);
		useBackground();
		mVerticalMovable=false;
	}
	
	public void moveLeft(int offset) {
		if(offset>0)
			super.movePiece(-offset,0);
	}
	
	public void moveRight(int offset) {
		if(offset>0)
			super.movePiece(offset,0);
	}
	
	public void setVerticalMovable(boolean isMovable) {
		mVerticalMovable=isMovable;
	}
	
	@Override
	public void movePiece(int horizontal,int vertical){
		if(mVerticalMovable)
			super.movePiece(horizontal,vertical);
		else
			super.movePiece(horizontal,0);
	}
}
