package models;

import java.awt.Point;

import values.StringValue;

public class MainPiece extends HorizontalShortPiece {
	
	public MainPiece(int positionX,int positionY) {
		super(positionX,positionY);
		setBackground(StringValue.MAIN_PIECE_IMG);
		setClickedBackground(StringValue.CLICKED_MAIN_PIECE_IMG);
		useBackground();
	}
}
