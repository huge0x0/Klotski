package controllers;

import models.CheckerBoard;

public class PlayHu {
	private static PlayHu sPlayHu;
	
	CheckerBoard mCheckerBoard;
	
	static PlayHu getController(){
		if(sPlayHu==null)
			sPlayHu=new PlayHu();
		return sPlayHu;
	}
	
	public PlayHu() {
		
	}
}
