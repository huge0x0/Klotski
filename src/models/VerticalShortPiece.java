package models;

import values.StringValue;

public class VerticalShortPiece extends Piece {
	
	public VerticalShortPiece(int positionX,int positionY) {
		super(positionX,positionY,1,2);
		setBackground(StringValue.SHORT_PIECE_IMG);
		setClickedBackground(StringValue.CLICKED_SHORT_PIECE_IMG);
		useBackground();
		mHorizontalMovable=false;
	}

	public void moveUp(int offset) {
		if(offset>0)
			super.movePiece(0,-offset);
	}
	
	public void moveDown(int offset) {
		if(offset>0)
			super.movePiece(0,offset);
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

}
