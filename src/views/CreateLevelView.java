package views;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.CheckerBoard;
import models.CreateLevelBoard;
import models.HorizontalLongPiece;
import models.HorizontalShortPiece;
import models.MainPiece;
import models.Piece;
import models.VerticalLongPiece;
import models.VerticalShortPiece;
import values.IntValue;

public class CreateLevelView extends JPanel{
	
	private CreateLevelBoard mCheckerBoard;
	private HorizontalLongPiece mHorizontalLongPiece;
	private HorizontalShortPiece mHorizontalShortPiece;
	private VerticalLongPiece mVerticalLongPiece;
	private VerticalShortPiece mVerticalShortPiece;
	private MainPiece mMainPiece;
	
	public CreateLevelView() {
		mCheckerBoard=new CreateLevelBoard();
		mHorizontalLongPiece=new HorizontalLongPiece(0, 0);
		mHorizontalShortPiece=new HorizontalShortPiece(0, 1*IntValue.UNIT_LEN);
		mVerticalLongPiece=new VerticalLongPiece(3*IntValue.UNIT_LEN, 0);
		mVerticalLongPiece.setHorizontalMovable(true);
		mVerticalShortPiece=new VerticalShortPiece(4*IntValue.UNIT_LEN, 0);
		mVerticalShortPiece.setHorizontalMovable(true);
		mMainPiece=new MainPiece(0, 2*IntValue.UNIT_LEN);
		mMainPiece.setVerticalMovable(true);

		setLayout(null);
		mCheckerBoard.setLocation(0, 3*IntValue.UNIT_LEN);
		add(mCheckerBoard);
		add(mHorizontalLongPiece);
		add(mHorizontalShortPiece);
		add(mVerticalLongPiece);
		add(mVerticalShortPiece);
		add(mMainPiece);
		
		
	}
	
	public CreateLevelBoard getCheckerBoard() {
		return mCheckerBoard;
	}

	public HorizontalLongPiece getHorizontalLongPiece() {
		return mHorizontalLongPiece;
	}

	public HorizontalShortPiece getHorizontalShortPiece() {
		return mHorizontalShortPiece;
	}

	public VerticalLongPiece getVerticalLongPiece() {
		return mVerticalLongPiece;
	}

	public VerticalShortPiece getVerticalShortPiece() {
		return mVerticalShortPiece;
	}

	public MainPiece getMainPiece() {
		return mMainPiece;
	}

	static public void main(String[] args){
		JFrame frame=new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CreateLevelView view=new CreateLevelView();
		Piece myPiece=new HorizontalLongPiece(0, 0);
		Piece myPiece2=new VerticalShortPiece(0, 0);
		MainPiece mainPiece=new MainPiece(0, 0);
		view.getCheckerBoard().addPieceForInfo(myPiece, 0, 0,1);
		view.getCheckerBoard().addPieceForInfo(myPiece2, 3, 1,1);
		view.getCheckerBoard().addPieceForInfo(mainPiece, 0, 1,1);
		
		frame.getContentPane().setLayout(null);
		view.setSize(800, 800);
		frame.getContentPane().add(view);
		frame.setVisible(true);
	}
}
