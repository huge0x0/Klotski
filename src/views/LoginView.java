package views;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.naming.directory.SearchControls;
import javax.swing.*;

public class LoginView extends JFrame{
	public static void main(String[] args)
	   {
		LoginView  frame = new LoginView ();
		 frame.setSize(500, 400);
		 frame.setTitle("登录");
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.show();
	  }
	
	JButton jb1,jb2;  
    JTextField jtf;  
    JPasswordField jpwd;  
    JLabel jl1,jl2;  
    JPanel jp1,jp2,jp3,jp4;  
   
    public LoginView()  
    {   
    	//创建组件  
        jb1=new JButton("登录"); 
        jb2=new JButton("注册");  
        jtf=new JTextField(10);  //账号
        jpwd=new JPasswordField(10);  //密码

        jpwd.setEchoChar('*');  //密码安全
        jl1=new JLabel("账    号：");  
        jl2=new JLabel("密    码：");  
        //设置Panel
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();
        //设置布局管理器  
        this.setLayout(new GridLayout(4,2,5,5));
        //添加组件  
        jp2.add(jl1);  
        jp2.add(jtf); 
        jp3.add(jl2);   
        jp3.add(jpwd);  
        jp4.add(jb1);   
        jp4.add(jb2); 
        //添加Panel  
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3); 
        this.add(jp4); 
        //设置监听器
        jb1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		try {
					if(LoginandRegister.search(jtf.getText(), jpwd.getText())){  //判断用户名是否正确，进入游戏界面
						System.out.println("OK");  //画面转换接口
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        });
        jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					LoginandRegister.add(jtf.getText(), jpwd.getText());//添加用户信息到文本文件中
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
        
        });
    }  
}

class LoginandRegister{//登录查询
	static boolean search(String user,String pword)throws IOException{
		String[][] yonghu=new String[10][2];
		File file = new File("E:\\javafiles\\Klotski\\src\\views\\用户密码.txt");//存放数组数据的文件
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
	static void add(String user,String pword)throws IOException{  
        String temp="\r\n"+user+"\t"+pword;  
        String temp2=user+"\t"+pword;
        File file = new File("E:\\javafiles\\Klotski\\src\\views\\用户密码.txt");  
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

 