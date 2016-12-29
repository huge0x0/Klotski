package views;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.ScoreModel;
import models.ScoreModel.ScoreHolder;
import values.IntValue;

public class ShowScoreView extends JPanel{
	static public final int BUTTON_PLAY_AGAIN=0;
	static public final int BUTTON_RETURN=1;
	static public final int BUTTON_NEXT=2;
	private RankListPanel mRankList;
	private Button mButtonPlayAgain;
	private Button mButtonReturn;
	private Button mButtonNext;
	private static final int BOUNDHEIGHT=30;
	
	public ShowScoreView(ScoreHolder scoreHolder) {
		mButtonPlayAgain=new Button("play again");
		mButtonReturn=new Button("return");
		mButtonNext=new Button("next");
		Box vBox=Box.createVerticalBox();
		vBox.add(Box.createVerticalStrut(50));
		Box hBox=Box.createHorizontalBox();
		hBox.add(Box.createGlue());
		mRankList=new RankListPanel(scoreHolder);
		hBox.add(mRankList);
		hBox.add(Box.createGlue());
		vBox.add(hBox);
		vBox.add(Box.createGlue());
		Box hButtonBox=Box.createHorizontalBox();
		hButtonBox.add(Box.createGlue());
		hButtonBox.add(mButtonNext);
		hButtonBox.add(Box.createGlue());
		hButtonBox.add(mButtonPlayAgain);
		hButtonBox.add(Box.createGlue());
		hButtonBox.add(mButtonReturn);
		hButtonBox.add(Box.createGlue());
		vBox.add(hButtonBox);
		vBox.setSize(IntValue.CHECKERBOARD_WIDTH*IntValue.UNIT_LEN, IntValue.CHECKERBOARD_HEIGHT*IntValue.UNIT_LEN);
		add(vBox);
	}
	
	public Button getButton(int name){
		switch (name) {
		case BUTTON_PLAY_AGAIN:
			return mButtonPlayAgain;
		case BUTTON_NEXT:
			return mButtonNext;
		case BUTTON_RETURN:
			return mButtonReturn;
		default:
			return null;
		}
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
			Label userScoreLabel=new Label("用时");
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
		ShowScoreView myTestView=new ShowScoreView(mytestHolder);
		myFrame.getContentPane().add(myTestView);
		myFrame.setVisible(true);
	}
}
