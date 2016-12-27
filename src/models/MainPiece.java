package models;

import java.awt.Point;

import values.StringValue;

public class MainPiece extends HorizontalShortPiece {
	private int mDestinationX;
	private int mDestinationY;
	
	public MainPiece(int positionX,int positionY,int destinationX,int destinationY) {
		super(positionX,positionY);
		mDestinationX=destinationX-getWidth();
		mDestinationY=destinationY-getHeight();
		getPieceView().setBackground(StringValue.MAIN_PIECE_IMG);
		getPieceView().setClickedBackground(StringValue.CLICKED_MAIN_PIECE_IMG);
		getPieceView().useBackground();
	}
	
	public Point getDestination(){
		return new Point(mDestinationX, mDestinationY);
	}

	public int getDestinationX() {
		return mDestinationX;
	}

	public int getDestinationY() {
		return mDestinationY;
	}
	
}
