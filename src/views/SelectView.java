package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import models.LevelModel;
import models.LevelModel.PieceInformation;

public class SelectView extends JPanel {
	private int mLevelNum;
	private ActionListener mButtonListener;
	private JButton mButtonReturn;
	private JButton mButtonNext;

	public SelectView(int level, ActionListener buttonListener) {
		mButtonListener = buttonListener;
		mLevelNum = level;
		jbInit();
	}

	void jbInit() {
		setLayout(new GridLayout(8, 3));
		JButton[] jb = new JButton[mLevelNum];
		for (int i = 0; i < mLevelNum; i++) {
			jb[i] = new JButton("Level " + (i + 1));
		}
		for (int i = 0; i < mLevelNum; i++) {
			add(jb[i]);
		}
		for (int i = 0; i < mLevelNum; i++) {
			jb[i].addActionListener(mButtonListener);
		}
		// contentPane.add(new JButton("Privious"));
		add(new JButton("Back"));
		add(new JButton("Next"));
		setSize(500, 400);
		setVisible(true);

	}
	
	

	public JButton getButtonReturn() {
		return mButtonReturn;
	}

	public JButton getButtonNext() {
		return mButtonNext;
	}

	public static void main(String args[]) {
		JFrame myFrame = new JFrame();
		myFrame.setSize(500, 500);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*SelectView selectView = new SelectView(3);
		myFrame.getContentPane().add(selectView);*/
		myFrame.setVisible(true);
	}

}
