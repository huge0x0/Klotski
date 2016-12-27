package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import models.ScoreModel.ScoreHolder;
import values.IntValue;
import values.StringValue;

public class ScoreModel {
	
	private static ScoreModel sScoreModel;
	
	private File mFileScoreFile;
	
	public static ScoreModel getModel() {
		if(sScoreModel==null)
			sScoreModel=new ScoreModel();
		return sScoreModel;
	}
	
	public ScoreModel() {
		mFileScoreFile=new File(StringValue.SCORE_FILE);
		
		if(!mFileScoreFile.exists()){
			try{
				mFileScoreFile.createNewFile();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ScoreHolder getScore(){
		//创建返回的数据类
		ScoreHolder scoreHolder=new ScoreHolder();
		
		//创建Scanner
		Scanner fileScanner=null;
		try {
			fileScanner=new Scanner(mFileScoreFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//读取数据
		for(int i=0;fileScanner.hasNextLine()&&i<10;i++){
			scoreHolder.setScore(fileScanner.next(), fileScanner.nextInt());
		}
		
		//关闭Scanner
		fileScanner.close();
		
		return scoreHolder;
	}
	
	public void setScore(ScoreHolder scoreHolder){
		//获取排行榜长度、用户名、分数
		int len=scoreHolder.getLength();
		String[] userName=scoreHolder.getUserName();
		int[] userScore=scoreHolder.getUserScore();
		
		//创建writer
		try {
	         BufferedWriter out = new BufferedWriter(new FileWriter(mFileScoreFile));
	         for(int i=0;i<len;i++){
	        	 out.write(userName[i]+"  "+userScore[i]);
	        	 if(i<len-1)
	        		 out.write("\r\n");
	         }
	         out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//写入文件
		for(int i=0;i<len;i++){
			
		}
		
	}
	
	public class ScoreHolder{
		
		private String[] mUserName;
		private int[] mUserScore;
		private int num;
		
		public ScoreHolder() {
			mUserName=new String[IntValue.SCORE_LENGTH];
			mUserScore=new int[IntValue.SCORE_LENGTH];
			num=-1;
		}
		
		public int getLength() {
			return num+1;
		}
		
		public String[] getUserName() {
			return mUserName;
		}
		
		public int[] getUserScore() {
			return mUserScore;
		}
		
		public void setScore(String userName,int userScore) {
			num++;
			if(num<IntValue.SCORE_LENGTH){
				mUserScore[num] = userScore;
				mUserName[num] = userName;
				
				for(int i=num-1;i>=0;i--){
					if(mUserScore[i+1]>mUserScore[i])
						change(i, i+1);
				}
			}else
				num--;
		}
		
		private void change(int a,int b) {
			String strTemp;
			int intTemp;
			
			strTemp=mUserName[a];
			mUserName[a]=mUserName[b];
			mUserName[b]=strTemp;
			
			intTemp=mUserScore[a];
			mUserScore[a]=mUserScore[b];
			mUserScore[b]=intTemp;
		}
	}
	
	public static void main(String[] args) {
		ScoreModel scoreModel=ScoreModel.getModel();
		ScoreHolder scoreHolder=scoreModel.new ScoreHolder();
		scoreHolder.setScore("a", 20);
		scoreHolder.setScore("b", 10);
		scoreHolder.setScore("c", 30);
		scoreModel.setScore(scoreHolder);
		
		scoreHolder=scoreModel.getScore();
		int len=scoreHolder.getLength();
		String[] userName=scoreHolder.getUserName();
		int[] userScore=scoreHolder.getUserScore();
		for(int i=0;i<len;i++)
			System.out.println(userName[i]+' '+userScore[i]+"\n");
	}
}
