package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.GapContent;

import views.HomePageView;

public class HomePage {
	static private HomePage sHomePage;
	
	static public HomePage getController(){
		if(sHomePage==null)
			sHomePage=new HomePage();
		return sHomePage;
	}
	
	public HomePage() {
		
	}
	
	public HomePageView getView(){
		HomePageView homePageView=new HomePageView();
		homePageView.getButtonChoseLevel().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HomePage.getController().choseLevel();
				
			}
		});
		
		homePageView.getButtonCreateLevel().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HomePage.getController().createLevel();
				
			}
		});
		
		return homePageView;
	}
	
	private void choseLevel(){
		GameFrame.getFrame().homePageToChoseLevel();
	}
	
	private void createLevel() {
		GameFrame.getFrame().homePageToCreateLevel();
	}
}
