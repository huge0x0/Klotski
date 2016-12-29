package models;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.LevelModel.PieceInformation;

public class CreateLevelBoard extends CheckerBoard {
	
	private ArrayList<PieceInformation> mInformations;
	private LevelModel mLevelModel;
	
	public CreateLevelBoard() {
		mInformations=new ArrayList<>();
		mLevelModel=LevelModel.getModel();
	}
		
	public void addPieceForInfo(Piece piece,int x,int y,int kind){
		if(addPiece(piece, x, y)!=-1)
			mInformations.add(mLevelModel.new PieceInformation(x, y, kind));
	}
	
	public PieceInformation[] getInformations(){
		PieceInformation[] pieceInformations=new LevelModel.PieceInformation[mInformations.size()];
		mInformations.toArray(pieceInformations);
		return pieceInformations;
	}
	
	
	static public void main(String[] args){
		JFrame frame=new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel(null);
		
		CreateLevelBoard model=new CreateLevelBoard();
		Piece myPiece=new HorizontalLongPiece(0, 0);
		Piece myPiece2=new VerticalShortPiece(0, 0);
		MainPiece mainPiece=new MainPiece(0, 0);
		/*int piece1Num=model.addNormalPiece(myPiece, 0, 0);
		int piece2Num=model.addNormalPiece(myPiece2, 3, 1);
		int mainPieceNum=model.addMainPiece(mainPiece, 0, 1);*/
		model.addPieceForInfo(myPiece, 0, 0,1);
		model.addPieceForInfo(myPiece2, 3, 1,1);
		model.addPieceForInfo(mainPiece, 0, 1,1);
		
		/*LevelModel levelModel=new LevelModel();
		PieceInformation[] pieceInformations=new LevelModel.PieceInformation[2];
		pieceInformations[0]=levelModel.new PieceInformation(3, 0, PieceInformation.VERTICAL_LONG);
		pieceInformations[1]=levelModel.new PieceInformation(0, 1, PieceInformation.MAIN);
		
		CheckerBoard model=new CheckerBoard(pieceInformations);*/
		
		panel.add(model);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
		//model.PieceReturn(myPiece, 3, 3);
	}
}
