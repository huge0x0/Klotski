package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import models.CheckerBoard;
import values.IntValue;

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
