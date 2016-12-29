package controllers;

import java.awt.Choice;
import java.awt.Container;

import javax.swing.JFrame;

import models.LevelModel;
import models.LevelModel.PieceInformation;
import values.IntValue;
import views.LoginView;
import views.ShowScoreView;

public class GameFrame extends JFrame{
	private static GameFrame sGameFrame;
	
	private String mUserName;
	
	private PieceInformation[] mPieceInformations;
	private int mLevel;
	
	public static GameFrame getFrame(){
		if(sGameFrame==null){
			sGameFrame=new GameFrame();
		}
		return sGameFrame;
	}
	
	public GameFrame() {
	}
	
	public void startGame() {
		login();
	}
	
	public void loginCompelete(String userName){
		mUserName=userName;
		choseLevel();
	}
	
	public void choseAgain(){
		choseLevel();
	}

	public void choseFinish(PieceInformation[] pieceInformations,int level) {
		mPieceInformations=pieceInformations;
		mLevel=level;
		play();
	}
	
	public void playSucceed(long playTime){
		showScore(playTime);
	}
	
	public void playAgain(){
		play();
	}
	
	private void login() {
		getContentPane().removeAll();
		LoginView loginView=new LoginView();
		getContentPane().add(loginView);
		repaint();
	}
	
	
	private void play() {
		getContentPane().removeAll();
		Play play=Play.getController(mPieceInformations);
		getContentPane().add(play.getPlayView());
		validate();
		repaint();
	}
	
	private void showScore(long playTime) {
		getContentPane().removeAll();
		int score=(int)playTime;
		ShowScore showScore=ShowScore.getController(mLevel);
		showScore.setScore(mUserName, score);
		ShowScoreView showScoreView=showScore.getScoreView();
		getContentPane().add(showScoreView);
		validate();
		repaint();
	}
	
	private void choseLevel() {
		getContentPane().removeAll();
		/*ChoseLevel choseLevel=ChoiceLevel.getPanel();
		getContentPane().add(choseLevel);*/
		
		LevelModel levelModel=new LevelModel();
		PieceInformation[] pieceInformations=new LevelModel.PieceInformation[2];
		pieceInformations[0]=levelModel.new PieceInformation(3, 0, PieceInformation.VERTICAL_LONG);
		pieceInformations[1]=levelModel.new PieceInformation(0, 1, PieceInformation.MAIN);
		choseFinish(pieceInformations,1);
	}

	static public void main(String[] args){
		GameFrame gameFrame=GameFrame.getFrame();
		gameFrame.startGame();
		gameFrame.setVisible(true);
		gameFrame.setSize(IntValue.WINDOW_WIDTH, IntValue.WINDOW_HEIGHT);
	}
}
