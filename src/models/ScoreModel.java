package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ScoreModel {
	
	private File mFileScoreFile;
	private Scanner mFileScanner;
	
	public ScoreModel() {
		String filePath="res\\score.txt";
		mFileScoreFile=new File(filePath);
		
		if(!mFileScoreFile.exists()){
			try{
				mFileScoreFile.createNewFile();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			mFileScanner=new Scanner(mFileScoreFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public ScoreHolder getScore(){
		ScoreHolder scoreHolder=new ScoreHolder();
		
		for(int i=0;mFileScanner.hasNextLine()&&i<10;i++){
			scoreHolder.setScore(mFileScanner.next(), mFileScanner.next(), i);
		}
	}
	
	public class ScoreHolder{
		String[] mUserName;
		String[] mUserScore;
		
		public ScoreHolder() {
			mUserName=new String[10];
			mUserName=new String[10];
		}
		
		public String[] getUserName() {
			return mUserName;
		}
		
		public String[] getUserScore() {
			return mUserScore;
		}
		
		public void setScore(String userName,String userScore,int position) {
			this.mUserScore[position] = userScore;
			this.mUserName[position] = userName;
		}
		
	}
}
