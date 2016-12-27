package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LoginAndRegister {
	public static boolean search(String user,String pword)throws IOException{
		String[][] yonghu=new String[10][2];
		File file = new File("res\\userid.txt");//存放数组数据的文件
		//FileWriter out = new FileWriter(file);  //文件写入流
		BufferedReader in = new BufferedReader(new FileReader(file));  //
		String line;  //一行数据
		int row=0;
		//逐行读取，并将每个数组放入到数组中
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
		/*for(int i=0;i<10;i++){   //测试数组内容
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
        	FileOutputStream fos = new FileOutputStream(file,true);//true表示在文件末尾追加  
            fos.write(temp.getBytes()); 
            fos.close();//流要及时关闭    
        } 
        else{
        	FileOutputStream fos = new FileOutputStream(file,true);//true表示在文件末尾追加  
            fos.write(temp2.getBytes()); 
            fos.close();//流要及时关闭    
        }
        
    }  
}
