package views;

import javax.swing.Box;
import javax.swing.JPanel;

import models.CheckerBoard;
import models.HorizontalLongPiece;
import models.HorizontalShortPiece;
import models.VerticalLongPiece;
import models.VerticalShortPiece;

public class CreateLevelView extends JPanel{
	
	private CheckerBoard mCheckerBoard;
	private HorizontalLongPiece mHorizontalLongPiece;
	private HorizontalShortPiece mHorizontalShortPiece;
	private VerticalLongPiece mVerticalLongPiece;
	private VerticalShortPiece mVerticalShortPiece;
	
	public CreateLevelView() {
		mCheckerBoard=new CheckerBoard();
		mHorizontalLongPiece=new HorizontalLongPiece(0, 0);
		mHorizontalShortPiece=new HorizontalShortPiece(0, 0);
		mVerticalLongPiece=new VerticalLongPiece(0, 0);
		mVerticalShortPiece=new VerticalShortPiece(0, 0);
		
		Box vBox=Box.createVerticalBox();
		vBox.add(m)
	}
}
