package controllers;

import java.awt.Choice;
import java.awt.Container;

import javax.swing.JFrame;

import models.LevelModel.PieceInformation;
import views.LoginView;
import views.ShowScoreView;

public class GameFrame extends JFrame{
	private static GameFrame sGameFrame;
	
	private Container mContentPanel;
	
	private String mUserName;
	
	public static GameFrame getFrame(){
		if(sGameFrame==null){
			sGameFrame=new GameFrame();
		}
		return sGameFrame;
	}
	
	public void GameFrame() {
		mContentPanel=this.getContentPane();
	}
	
	public void startGame() {
		login();
	}
	
	public void loginCompelete(String userName){
		mUserName=userName;
		choseLevel();
	}

	public void choseFinish(PieceInformation[] pieceInformations) {
		play(pieceInformations);
		
	}
	
	public void playSucceed(long playTime){
		showScore(playTime);
	}
	
	private void login() {
		mContentPanel.removeAll();
		LoginView loginView=new LoginView();
		mContentPanel.add(loginView);
	}
	
	
	private void play(PieceInformation[] pieceInformations) {
		mContentPanel.removeAll();
		Play play=Play.getController(pieceInformations);
		mContentPanel.add(playPanel);
	}
	
	private void showScore(long playTime) {
		mContentPanel.removeAll();
		int score=(int)playTime;
		ShowScore showScore=ShowScore.getController();
		showScore.setScore(mUserName, score);
		ShowScoreView showScoreView=showScore.getScoreView();
		mContentPanel.add(showScoreView);
	}
	
	private void choseLevel() {
		mContentPanel.removeAll();
		ChoseLevel choseLevel=ChoiceLevel.getPanel();
		mContentPanel.add(choseLevel);
	}

}
