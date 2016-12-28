package controllers;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import models.ScoreModel;
import models.ScoreModel.ScoreHolder;
import views.ShowScoreView;

public class ShowScore{
	private static ShowScore sShowScore;
	
	private ScoreHolder mScoreHolder;
	private ScoreModel mScoreModel;
	private int mLevel;
	
	public static ShowScore getController(int level) {
		if(sShowScore==null)
			sShowScore=new ShowScore();
		sShowScore.setLevel(level);
		return sShowScore;
	}
	
	public static ShowScore getController() {
		if(sShowScore==null)
			sShowScore=new ShowScore();
		return sShowScore;
	}
	
	public ShowScore() {
		setLevel(1);
	}
	
	private void setLevel(int level){
		mLevel=level;
		mScoreModel=ScoreModel.getModel(mLevel);
		mScoreHolder=mScoreModel.getScore();
	}
	
	public void setScore(String userName,int userScore) {
		mScoreHolder.setScore(userName, userScore);
		mScoreModel.setScore(mScoreHolder);
	}
	
	public ShowScoreView getScoreView() {
		ShowScoreView showScoreView=new ShowScoreView(mScoreHolder);
		showScoreView.getButton(ShowScoreView.BUTTON_PLAY_AGAIN).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowScore.getController().playAgain();
			}
		});
		return showScoreView;
	}
	
	private void  playAgain(){
		GameFrame gameFrame=GameFrame.getFrame();
		gameFrame.playAgain();
	}
	
	public static void main(String[] args) {
		JFrame jFrame=new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(500, 500);
		ShowScore showScore=ShowScore.getController();
		ShowScoreView showScoreView=showScore.getScoreView();
		jFrame.add(showScoreView);
		jFrame.setVisible(true);
	}
}
