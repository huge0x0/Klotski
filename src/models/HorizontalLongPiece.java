package models;

import values.StringValue;

public class HorizontalLongPiece extends Piece {
	private boolean mVerticalMovable;
	
	public HorizontalLongPiece(int positionX,int positionY) {
		super(positionX,positionY,3,1);
		getPieceView().setBackground(StringValue.LONG_PIECE_IMG);
		getPieceView().setClickedBackground(StringValue.CLICKED_LONG_PIECE_IMG);
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
