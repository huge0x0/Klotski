package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LoginAndRegister {
	public static boolean search(String user,String pword)throws IOException{
		String[][] yonghu=new String[10][2];
		File file = new File("res\\userid.txt");//����������ݵ��ļ�
		//FileWriter out = new FileWriter(file);  //�ļ�д����
		BufferedReader in = new BufferedReader(new FileReader(file));  //
		String line;  //һ������
		int row=0;
		//���ж�ȡ������ÿ��������뵽������
		while((line = in.readLine()) != null){
			String[] temp = line.split("\t"); 
			for(int j=0;j<temp.length;j++){
				yonghu[row][j] = temp[j];
			}	
			row++;
		}
		in.close();
		int k=0;
		for(int i=0;i<10;i++){
				if(user.equals(yonghu[i][0])&&pword.equals(yonghu[i][1])){
					k=1;
				}
		}
		/*for(int i=0;i<10;i++){   //������������
			for(int j=0;j<2;j++){
				System.out.print(yonghu[i][j]+"\t");
			}
			System.out.println();
		}*/
		if(k==1)
			return true;
		else
			return false;	
	}
	public static void add(String user,String pword)throws IOException{  
        String temp="\r\n"+user+"\t"+pword;  
        String temp2=user+"\t"+pword;
        File file = new File("res\\userid.txt");  
        if(file.exists() && file.length() != 0) {  
        	FileOutputStream fos = new FileOutputStream(file,true);//true��ʾ���ļ�ĩβ׷��  
            fos.write(temp.getBytes()); 
            fos.close();//��Ҫ��ʱ�ر�    
        } 
        else{
        	FileOutputStream fos = new FileOutputStream(file,true);//true��ʾ���ļ�ĩβ׷��  
            fos.write(temp2.getBytes()); 
            fos.close();//��Ҫ��ʱ�ر�    
        }
        
    }  
}
