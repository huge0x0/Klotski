package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import models.CheckerBoard;
import models.CreateLevelBoard;
import models.HorizontalLongPiece;
import models.HorizontalShortPiece;
import models.LevelModel;
import models.MainPiece;
import models.Piece;
import models.VerticalLongPiece;
import models.VerticalShortPiece;
import models.LevelModel.PieceInformation;
import values.IntValue;
import views.CreateLevelView;

public class CreateLevel {
	private static CreateLevel sCreateLevel;
	
	
	private CreateLevelBoard mCheckerBoard;
	
	private LevelModel mLevelModel;
	
	private CreateLevelView mCreateLevelView;
	
	
	static public CreateLevel getController(){
		if(sCreateLevel==null)
			sCreateLevel=new CreateLevel();
		return sCreateLevel;
	}
	
	public CreateLevel() {
		mLevelModel=new LevelModel();
	}
	
	public CreateLevelView getView(){
		mCreateLevelView=new CreateLevelView();
		mCheckerBoard=mCreateLevelView.getCheckerBoard();
		PieceDragListener pieceDragListener1=new PieceDragListener(PieceInformation.HORIZONTAL_LONG);
		PieceDragListener pieceDragListener2=new PieceDragListener(PieceInformation.HORIZONTAL_SHORT);
		PieceDragListener pieceDragListener3=new PieceDragListener(PieceInformation.VERTICAL_LONG);
		PieceDragListener pieceDragListener4=new PieceDragListener(PieceInformation.VERTICAL_SHORT);
		PieceDragListener pieceDragListener5=new PieceDragListener(PieceInformation.MAIN);
		
		mCreateLevelView.getHorizontalLongPiece().addMouseListener(pieceDragListener1);
		mCreateLevelView.getHorizontalShortPiece().addMouseListener(pieceDragListener2);
		mCreateLevelView.getVerticalLongPiece().addMouseListener(pieceDragListener3);
		mCreateLevelView.getVerticalShortPiece().addMouseListener(pieceDragListener4);
		mCreateLevelView.getMainPiece().addMouseListener(pieceDragListener5);
		
		mCreateLevelView.getHorizontalLongPiece().addMouseMotionListener(pieceDragListener1);
		mCreateLevelView.getHorizontalShortPiece().addMouseMotionListener(pieceDragListener2);
		mCreateLevelView.getVerticalLongPiece().addMouseMotionListener(pieceDragListener3);
		mCreateLevelView.getVerticalShortPiece().addMouseMotionListener(pieceDragListener4);
		mCreateLevelView.getMainPiece().addMouseMotionListener(pieceDragListener5);
		
		return mCreateLevelView;
	}
	
	private class PieceDragListener implements MouseListener, MouseMotionListener{
		
		private int mPieceType;
		
		//拖移目标
		private Piece mPiece;

		//鼠标起始位置
		private int mStartPositionX;
		private int mStartPositionY;
		
		
		public PieceDragListener(int type) {
			mPieceType=type;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int positionDX=e.getX()-mStartPositionX;
			int positionDY=e.getY()-mStartPositionY;
			mPiece.movePiece(positionDX, positionDY);
			
			mStartPositionX=e.getX();
			mStartPositionY=e.getY();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			mStartPositionX=e.getX();
			mStartPositionY=e.getY();
			
			Piece piece=null;
			switch (mPieceType) {
			case PieceInformation.HORIZONTAL_LONG:
				piece=new HorizontalLongPiece(0, 0);
				((HorizontalLongPiece)piece).setVerticalMovable(true);
				break;
			case PieceInformation.HORIZONTAL_SHORT:
				piece=new HorizontalShortPiece(0, 1*IntValue.UNIT_LEN);
				((HorizontalShortPiece)piece).setVerticalMovable(true);
				break;
			case PieceInformation.VERTICAL_SHORT:
				piece=new VerticalShortPiece(4*IntValue.UNIT_LEN, 0);
				((VerticalShortPiece)piece).setHorizontalMovable(true);
				break;
			case PieceInformation.VERTICAL_LONG:
				piece=new VerticalLongPiece(3*IntValue.UNIT_LEN, 0);
				((VerticalLongPiece)piece).setHorizontalMovable(true);
				break;
			case PieceInformation.MAIN:
				piece=new MainPiece(0, 2*IntValue.UNIT_LEN);
				((HorizontalShortPiece)piece).setVerticalMovable(true);
				break;
				
			}
			mPiece=piece;
			mCreateLevelView.add(mPiece, 0);
			//mCreateLevelView.validate();
			//mCreateLevelView.repaint();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int x=mPiece.getX()-mCheckerBoard.getX();
			int y=mPiece.getY()-mCheckerBoard.getY();
			x=x/IntValue.UNIT_LEN;
			y=y/IntValue.UNIT_LEN;

			System.out.print(mCheckerBoard.getX()+"\t");
			System.out.print(x+"\t");
			System.out.print(mCheckerBoard.getY()+"\t");
			System.out.println(y);
			
			mCheckerBoard.addPieceForInfo(mPiece, x, y, mPieceType);
			mCreateLevelView.remove(mPiece);
			mCreateLevelView.repaint();
			
		}
		
	}

	static public void main(String[] args){
		JFrame myFrame=new JFrame();
		myFrame.setSize(800, 800);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CreateLevel createLevel=CreateLevel.getController();
		
		myFrame.add(createLevel.getView());
		myFrame.setVisible(true);
	}
}
