package views;

import java.awt.Button;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.Play;
import models.CheckerBoard;
import models.LevelModel;
import models.LevelModel.PieceInformation;
import values.IntValue;

public class PlayView extends JPanel{
	private CheckerBoard mCheckerBoard;
	private Button mButtonReturn;
	
	public PlayView(CheckerBoard checkerBoard) {
		mCheckerBoard=checkerBoard;
		mButtonReturn=new Button("return");
		
		Box vBox=Box.createVerticalBox();
		vBox.add(checkerBoard);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(mButtonReturn);

		add(vBox);
	}
	
	public Button getButtonReturn(){
		return mButtonReturn;
	}
	
	static public void main(String[] args){
		JFrame myFrame=new JFrame();
		myFrame.setSize(500, 500);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LevelModel levelModel=new LevelModel();
		PieceInformation[] pieceInformations=new LevelModel.PieceInformation[2];
		pieceInformations[0]=levelModel.new PieceInformation(3, 0, PieceInformation.VERTICAL_LONG);
		pieceInformations[1]=levelModel.new PieceInformation(0, 1, PieceInformation.MAIN);
		
		CheckerBoard checkerBoard=new CheckerBoard(pieceInformations);
		PlayView playView=new PlayView(checkerBoard);
		
		myFrame.getContentPane().add(playView);
		myFrame.setVisible(true);
		
	}
}
