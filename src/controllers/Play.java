package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Date;
import java.sql.Time;

import javax.swing.JFrame;
import javax.xml.crypto.Data;

import models.CheckerBoard;
import models.LevelModel;
import models.LevelModel.PieceInformation;
import values.IntValue;
import views.PlayView;

public class Play {
	private static Play sPlay;
	
	private CheckerBoard mCheckerBoard;
	
	private long mStartTime;
	private long mEndTime;
	
	static public Play getController(LevelModel.PieceInformation[] pieceInformations){
		if(sPlay==null)
			sPlay=new Play();
		sPlay.setCheckerBoard(pieceInformations);
		return sPlay;
	}
	
	static public Play getController(){
		if(sPlay==null)
			sPlay=new Play();
		return sPlay;
	}
	
	public Play() {
	}
	
	private void setCheckerBoard(LevelModel.PieceInformation[] pieceInformations){
		mCheckerBoard=new CheckerBoard(pieceInformations);
	}
	
	public PlayView getPlayView(){
		PlayView playView=new PlayView(mCheckerBoard);
		playView.getButtonReturn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Play play=Play.getController();
				play.endPlay();
			}
		});
		startPlay();
		return playView;
	}
	
	public void startPlay(){
		mStartTime=System.currentTimeMillis();
	}
	
	public void succeedPlay(){
		mEndTime=System.currentTimeMillis();
		long useTime=(mEndTime-mStartTime)/1000;
		GameFrame.getFrame().playSucceed(useTime);
	}
	
	public void endPlay(){
		GameFrame.getFrame().returnHomePage();
	}
	
	static public void main(String[] args){
		JFrame myFrame=new JFrame();
		myFrame.setSize(500, 500);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LevelModel levelModel=new LevelModel();
		PieceInformation[] pieceInformations=new LevelModel.PieceInformation[2];
		pieceInformations[0]=levelModel.new PieceInformation(3, 0, PieceInformation.VERTICAL_LONG);
		pieceInformations[1]=levelModel.new PieceInformation(0, 1, PieceInformation.MAIN);
		
		Play play=Play.getController(pieceInformations);
		myFrame.getContentPane().add(play.getPlayView());
		myFrame.setVisible(true);
		
	}
}
