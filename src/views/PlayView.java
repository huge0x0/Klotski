package views;

import java.awt.Button;

import javax.swing.Box;
import javax.swing.JPanel;

import models.CheckerBoard;

public class PlayView extends JPanel{
	private CheckerBoard mCheckerBoard;
	private Button mButtonReturn;
	
	public PlayView(CheckerBoard checkerBoard) {
		mCheckerBoard=checkerBoard;
		mButtonReturn=new Button("return");
		
		Box vBox=Box.createVerticalBox();
		vBox.add(checkerBoard);
		vBox.add(mButtonReturn);

		add(vBox);
	}
	
	public Button getButtonReturn(){
		return mButtonReturn;
	}
}
