package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LevelModel {
	
	public static void main(String[] args) throws IOException{
		LevelModel LV=new LevelModel();
		PieceInformation[] p = new PieceInformation[2] ;
		PieceInformation[] t = new PieceInformation[2] ;
		p[0]=LV.new PieceInformation(1, 1, 1);
		p[1]=LV.new PieceInformation(2,2,2);
		PieceReceive(p);
		System.out.println("OK");
		t=LV.PieceBack();
		
	}
	
	public class PieceInformation{  //������Ϣ
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
	//File Level=new File("res\\Level\\");
	//File[] Levels=Level.listFiles();
	static File file=new File("res/PieceInf.txt");
	static void PieceReceive(PieceInformation[] p) throws IOException{//������Ϣд���ļ�
		FileWriter out = new FileWriter(file); //�ļ�д����
		for(int i=0;i<p.length;i++){
			out.write(String.valueOf(p[i].getX()));
			out.write("\t");
			out.write(String.valueOf(p[i].getY()));
			out.write("\t");
			out.write(String.valueOf(p[i].getkind()));
			out.write("\r\n");
		}
		out.close();
	}
	static PieceInformation[] PieceBack() throws IOException{//����������Ϣ
		
		BufferedReader in = new BufferedReader(new FileReader(file));  //
		String line,line2;  //һ������
		int row=0,row2=0;
	    //���ж�ȡ������ÿ��������뵽������
		while((line = in.readLine()) != null){
			 row++;
		}
		PieceInformation PieceInf[] = new PieceInformation[row];
		while((line2 = in.readLine()) != null){
			String[] temp = line2.split("\t"); 
			for(int i=0;i<row;i++){
				PieceInf[i].setX(Integer.parseInt(temp[0]));
				PieceInf[i].setY(Integer.parseInt(temp[1]));
				PieceInf[i].setkind(Integer.parseInt(temp[2]));
			}
			row2++;
		 }
		 in.close();
		 return PieceInf;
	}
}

