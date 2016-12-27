package models;

import values.StringValue;

public class VerticalShortPiece extends Piece {
	private boolean mHorizontalMovable;
	
	public VerticalShortPiece(int positionX,int positionY) {
		super(positionX,positionY,1,2);
		getPieceView().setBackground(StringValue.SHORT_PIECE_IMG);
		getPieceView().setClickedBackground(StringValue.CLICKED_SHORT_PIECE_IMG);
		getPieceView().useBackground();
		mHorizontalMovable=false;
	}

	public void moveUp(int offset) {
		if(offset>0)
			super.move(0,-offset);
	}
	
	public void moveDown(int offset) {
		if(offset>0)
			super.move(0,offset);
	}

	public void setHorizontalMovable(boolean isMovable) {
		mHorizontalMovable = isMovable;
	}
	
	@Override
	public void move(int horizontal,int vertical){
		if(mHorizontalMovable)
			super.move(horizontal,vertical);
		else
			super.move(0, vertical);
	}

}
