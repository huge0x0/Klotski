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
	
	public void login() {
		mContentPanel.removeAll();
		LoginPanel loginPanel=LoginPanel.getPanel();
		mContentPanel.add(loginPanel);
	}
	
	public void play() {
		mContentPanel.removeAll();
		PlayPanel playPanel=PlayPanel.getPanel();
		mContentPanel.add(playPanel);
	}
	
	public void showScore() {
		mContentPanel.removeAll();
		ShowScore showScore=ShowScore.getPanel();
		mContentPanel.add(showScore);
	}
	
	public void choseLevel() {
		mContentPanel.removeAll();
		ChoseLevel choseLevel=ChoiceLevel.getPanel();
		mContentPanel.add(choseLevel);
	}
}
