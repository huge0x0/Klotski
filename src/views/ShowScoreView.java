package views;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.ScoreModel;
import models.ScoreModel.ScoreHolder;

public class ShowScoreView extends JPanel{
	private RankListPanel mRankList;
	private Button mButton;
	private static final int BOUNDHEIGHT=30;
	
	public ShowScoreView(ScoreHolder scoreHolder,Button returnButton) {
		Box vBox=Box.createVerticalBox();
		vBox.add(Box.createVerticalStrut(50));
		Box hBox=Box.createHorizontalBox();
		hBox.add(Box.createGlue());
		mRankList=new RankListPanel(scoreHolder);
		hBox.add(mRankList);
		hBox.add(Box.createGlue());
		vBox.add(hBox);
		vBox.add(Box.createGlue());
		vBox.add(returnButton);
		add(vBox);
	}
	
	private class RankListPanel extends JPanel{
		final static int ITEMWIDTH=50;
		final static int ITEMHEIGHT=20;
		
		public RankListPanel(ScoreHolder scoreHolder) {
			String[] userNames=scoreHolder.getUserName();
			int[] userScores=scoreHolder.getUserScore();
			int len=scoreHolder.getLength();
			setSize(ITEMWIDTH*2, ITEMHEIGHT*len);

			GridLayout gridLayout=new GridLayout(len+1, 2,5,5);
			this.setLayout(gridLayout);
			
			Label userNameLabel=new Label("用户名");
			userNameLabel.setSize(ITEMWIDTH, ITEMHEIGHT);
			Label userScoreLabel=new Label("得分");
			userNameLabel.setSize(ITEMWIDTH, ITEMHEIGHT);
			add(userNameLabel);
			add(userScoreLabel);
			
			for(int i=0;i<len;i++){
				Label userNameItem=new Label(userNames[i]);
				userNameLabel.setSize(ITEMWIDTH, ITEMHEIGHT);
				Label userScoreItem=new Label(String.valueOf(userScores[i]));
				userNameLabel.setSize(ITEMWIDTH, ITEMHEIGHT);
				add(userNameItem);
				add(userScoreItem);
			}
		}
	}
	
	public static void main(String[] args){
		JFrame myFrame=new JFrame();
		myFrame.setSize(500, 500);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ScoreHolder mytestHolder=(new ScoreModel()).new ScoreHolder();
		mytestHolder.setScore("test", 222);
		mytestHolder.setScore("tes22t", 33);
		ShowScoreView myTestView=new ShowScoreView(mytestHolder,new Button("tetst"));
		myFrame.getContentPane().add(myTestView);
		myFrame.setVisible(true);
	}
}
