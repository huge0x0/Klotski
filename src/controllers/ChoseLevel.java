package controllers;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.plaf.SliderUI;

import models.LevelModel;
import models.LevelModel.PieceInformation;
import views.SelectView;

public class ChoseLevel {
	static private ChoseLevel sChoseLevel;
	
	private LevelModel mLevelModel;
	
	static public ChoseLevel getController(){
		if(sChoseLevel==null)
			sChoseLevel=new ChoseLevel();
		return sChoseLevel;
	}
	
	
	public ChoseLevel() {
		mLevelModel=LevelModel.getModel();
	}
	
	public SelectView getSelectView(){
		SelectView selectView=new SelectView(mLevelModel.getLevelNum(), new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
				//进入相应关卡
					int level=Integer.valueOf(((JButton)e.getSource()).getText().substring(6));
					PieceInformation[] pieceInformations=LevelModel.getModel().getPieceInfos(level);
					ChoseLevel.getController().choseFinish(pieceInformations, level);
			}
			});
		//selectView.getButtonNext().addActionListener(null);
		//selectView.getButtonNext().addActionListener(null);
		
		return selectView;
	}
	
	public void choseFinish(PieceInformation[] pieceInformations,int level){
		GameFrame.getFrame().choseFinish(pieceInformations, level);
	}
	
	public static void main(String args[]) {
		JFrame myFrame = new JFrame();
		myFrame.setSize(500, 500);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ChoseLevel choseLevel=ChoseLevel.getController();
		myFrame.getContentPane().add(choseLevel.getSelectView());
		myFrame.setVisible(true);
	}

}
