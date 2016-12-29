package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.loading.MLet;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.LevelModel.PieceInformation;
import values.StringValue;

public class LevelModel {
	private static LevelModel sLevelModel;
	
	public static LevelModel getModel(){
		if(sLevelModel==null)
			sLevelModel=new LevelModel();
		return sLevelModel;
	}
	
	
		//File Level=new File("res\\Level\\");
	//File[] Levels=Level.listFiles();
	private int mLevelNum;
	
	public int getLevelNum() {
		return mLevelNum;
	}

	public LevelModel() {
		File numFile=new File(StringValue.LEVEL_NUM_FILE);
		//创建Scanner
		Scanner fileScanner=null;
		try {
			fileScanner=new Scanner(numFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("321sd231f3sdf1s");
		}
		
		//读取数据
		mLevelNum=fileScanner.nextInt();
		
		//关闭Scanner
		fileScanner.close();
	}
	
	private void saveLevelNum() {
		File numFile=new File(StringValue.LEVEL_NUM_FILE);
		try {
			//创建writer
	         BufferedWriter out = new BufferedWriter(new FileWriter(numFile));
	         //写入文件
	         out.write(String.valueOf(mLevelNum));
	         
	         out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void setPieceInfos(PieceInformation[] pieceInformations){//棋子信息写入文件
		int len=pieceInformations.length;
		mLevelNum++;
		File file=new File(StringValue.LEVEL_FILE1+mLevelNum+StringValue.LEVEL_FILE2);
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		try {
			//创建writer
	         BufferedWriter out = new BufferedWriter(new FileWriter(file));
	         //写入文件
	         for(int i=0;i<len;i++){
	        	 out.write(pieceInformations[i].getX()+"  "+pieceInformations[i].getY()
	        			 +"  "+pieceInformations[i].getkind());
	        	 if(i<len-1)
	        		 out.write("\r\n");
	         }
	         out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		saveLevelNum();
	}
	
	
	public PieceInformation[] getPieceInfos(int level){//传出棋子信息
		
		if(level>mLevelNum)
			return null;
		
		File file=new File(StringValue.LEVEL_FILE1+level+StringValue.LEVEL_FILE2);
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		//创建Scanner
		Scanner fileScanner=null;
		try {
			fileScanner=new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//读取数据
		ArrayList<PieceInformation> pieceInformations=new ArrayList<>();
		while(fileScanner.hasNextLine()){
			pieceInformations.add(new PieceInformation(fileScanner.nextInt(), 
					fileScanner.nextInt(), fileScanner.nextInt()));
		}
		
		//关闭Scanner
		fileScanner.close();
		
		PieceInformation[] pieceInformations2=new PieceInformation[pieceInformations.size()];
		pieceInformations.toArray(pieceInformations2);
		return pieceInformations2;
	}
	
	public class PieceInformation{  //棋子信息
		public static final int MAIN=1;
		public static final int VERTICAL_SHORT=2;
		public static final int VERTICAL_LONG=3;
		public static final int HORIZONTAL_SHORT=4;
		public static final int HORIZONTAL_LONG=5;
		private int mX;
		private int mY;
		private int mkind;
		
		public PieceInformation(int X,int Y,int kind) {
			mX=X;
			mY=Y;
			mkind=kind;
		}

		public int getX() {
			return mX;
		}

		public void setX(int X) {
			mX = X;
		}

		public int getY() {
			return mY;
		}

		public void setY(int Y) {
			mY = Y;
		}

		public int getkind(){
			return mkind;
		}
		public void setkind(int kind){
			mkind=kind;
		}
	}


	public static void main(String[] args) throws IOException{
		LevelModel model=LevelModel.getModel();
		PieceInformation[] pieceInformations=new LevelModel.PieceInformation[2];
		pieceInformations[0]=model.new PieceInformation(3, 0, PieceInformation.VERTICAL_LONG);
		pieceInformations[1]=model.new PieceInformation(0, 1, PieceInformation.MAIN);
		model.setPieceInfos(pieceInformations);
		
		PieceInformation[] pieceInformations2=model.getPieceInfos(1);
		for (PieceInformation pieceInformation : pieceInformations2) {
			System.out.println(pieceInformation.getX()+"\t"+pieceInformation.getY()+"\t"+pieceInformation.getkind());
		}
		
	}
}

