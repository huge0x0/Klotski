package controllers;

import java.awt.Choice;
import java.awt.Container;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	private static GameFrame sGameFrame;
	
	private Container mContentPanel;
	
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
	public void loginCompelete(){
		play();
	}
	
	private void login() {
		mContentPanel.removeAll();
		LoginPanel loginPanel=LoginPanel.getPanel();
		mContentPanel.add(loginPanel);
	}
	
	
	private void play() {
		mContentPanel.removeAll();
		PlayPanel playPanel=PlayPanel.getPanel();
		mContentPanel.add(playPanel);
	}
	
	private void showScore() {
		mContentPanel.removeAll();
		ShowScore showScore=ShowScore.getController();
		ShowScoreView showScoreView=ShowScore.getPanel();
		mContentPanel.add(showScore);
	}
	
	private void choseLevel() {
		mContentPanel.removeAll();
		ChoseLevel choseLevel=ChoiceLevel.getPanel();
		mContentPanel.add(choseLevel);
	}
}
