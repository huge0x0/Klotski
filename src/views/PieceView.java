package views;


import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import values.IntValue;
import values.StringValue;

public class PieceView extends JLabel {
	
	private String mBgPath;
	private String mClickedBgPath;
	
	public PieceView(int positionX,int positionY,int width,int height) {
		width=width*IntValue.UNIT_LEN;
		height=height*IntValue.UNIT_LEN;
		mBgPath=StringValue.SHORT_PIECE_IMG;
		mClickedBgPath=StringValue.CLICKED_SHORT_PIECE_IMG;
		//setBackground(StringValue.SHORT_PIECE_IMG);
		setBounds(positionX, positionY, width, height);
	}
	
	public void setBackground(String bgPath) {
		mBgPath=bgPath;
	}
	
	public void useBackground() {
		setBg(mBgPath);
	}
	
	public void setClickedBackground(String bgPath){
		mClickedBgPath=bgPath;
	}
	
	public void useClickedBackground(){
		setBg(mClickedBgPath);
	}
	
	private void setBg(String bgPath) {
		ImageIcon imageIcon=new ImageIcon(bgPath);
		Image image=imageIcon.getImage();
		image=image.getScaledInstance(getWidth(),getHeight(), Image.SCALE_FAST);
		setIcon(new ImageIcon(image));
	}
	
	
	
	static public void main(String[] args){
		JFrame frame=new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel(null);
		PieceView view=new PieceView(0, 0, 1, 3);
		panel.add(view);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
