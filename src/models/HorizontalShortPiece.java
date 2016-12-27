package models;

import values.StringValue;

public class HorizontalShortPiece extends Piece {
	private boolean mVerticalMovable;
	
	public HorizontalShortPiece(int positionX,int positionY) {
		super(positionX,positionY,2,1);
		getPieceView().setBackground(StringValue.SHORT_PIECE_IMG);
		getPieceView().setClickedBackground(StringValue.CLICKED_SHORT_PIECE_IMG);
		getPieceView().useBackground();
		mVerticalMovable=false;
	}
	
	public void moveLeft(int offset) {
		if(offset>0)
			super.move(-offset,0);
	}
	
	public void moveRight(int offset) {
		if(offset>0)
			super.move(offset,0);
	}
	
	public void setVerticalMovable(boolean isMovable) {
		mVerticalMovable=isMovable;
	}
	
	@Override
	public void move(int horizontal,int vertical){
		if(mVerticalMovable)
			super.move(horizontal,vertical);
		else
			super.move(horizontal,0);
	}
}