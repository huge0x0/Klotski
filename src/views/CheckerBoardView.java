package views;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Piece;
import values.IntValue;

public class CheckerBoardView extends JLabel{
	static private final String FILEPATH="res\\checkerboard.jpg";
	
	
	public CheckerBoardView() {
		int width=IntValue.UNIT_LEN*IntValue.CHECKERBOARD_WIDTH;
		int height=IntValue.UNIT_LEN*IntValue.CHECKERBOARD_HEIGHT;
		//…Ë÷√±≥æ∞
		ImageIcon imageIcon=new ImageIcon(FILEPATH);
		Image image=imageIcon.getImage();
		image=image.getScaledInstance(width,height, Image.SCALE_FAST);
		setIcon(new ImageIcon(image));
		
		
		setBounds(0, 0, width*4, height);
	}
	
	public void addPiece(PieceView pieceView,int x,int y) {
		setPiece(pieceView, x, y);
		add(pieceView);
	}
	
	public void setPiece(PieceView pieceView,int x,int y) {
		x=(int)getLocation().getX()+x*IntValue.UNIT_LEN;
		y=(int)getLocation().getY()+y*IntValue.UNIT_LEN;
		pieceView.setLocation(x, y);
	}
	
	//≤‚ ‘
	static public void main(String[] args){
		JFrame frame=new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel(null);
		CheckerBoardView view=new CheckerBoardView();
		Piece myPiece=new Piece(0, 0, 1, 3);
		Piece myPiece2=new Piece(0, 0, 3, 1);
		
		view.addPiece(myPiece.getPieceView(),0,0);
		view.addPiece(myPiece2.getPieceView(),1,0);
		
		panel.add(view);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
