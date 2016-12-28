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

import controllers.GameFrame;
import controllers.LoginAndRegister;

public class LoginView extends JPanel{
	public static void main(String[] args)
	   {
		/*LoginView  frame = new LoginView ();
		 frame.setSize(500, 400);
		 frame.setTitle("登录");
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.show();*/
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
					if(LoginAndRegister.search(jtf.getText(), jpwd.getText())){  //判断用户名是否正确，进入游戏界面
						GameFrame.getFrame().loginCompelete(jtf.getText());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        });
        jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					LoginAndRegister.add(jtf.getText(), jpwd.getText());//添加用户信息到文本文件中
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
        
        });
    }  
}



 