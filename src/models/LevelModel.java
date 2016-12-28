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
		PieceReceive(3);
		System.out.println("OK");
	}
	public class PieceInformation{
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
	
	static File file=new File("res/Piecelnf.txt");
	static void PieceReceive(PieceInformation[] p) throws IOException{//棋子信息写入文件
		FileWriter out = new FileWriter(file); //文件写入流
		for(int i=0;i<p.length;i++){
			out.write(String.valueOf(p[i].getX()));
			out.write("\t");
			out.write(String.valueOf(p[i].getY()));
			out.write("\t");
			out.write(String.valueOf(p[i].getkind()));
			out.write("\n");
		}
		out.close();
	}
	static PieceInformation[] PieceBack() throws IOException{//传出棋子信息
		PieceInformation PieceInf[] = null;
		BufferedReader in = new BufferedReader(new FileReader(file));  //
		String line;  //一行数据
		  int row=0;
		  //逐行读取，并将每个数组放入到数组中
		  /*while((line = in.readLine()) != null){
		   String[] temp = line.split("\t"); 
		   for(int j=0;j<temp.length;j++){
		    arr2[row][j] = Double.parseDouble(temp[j]);
		   }
		   row++;
		  }
		  in.close();*/
		return PieceInf;
	}
}

