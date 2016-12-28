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
		 frame.setTitle("��¼");
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
    	//�������  
        jb1=new JButton("��¼"); 
        jb2=new JButton("ע��");  
        jtf=new JTextField(10);  //�˺�
        jpwd=new JPasswordField(10);  //����

        jpwd.setEchoChar('*');  //���밲ȫ
        jl1=new JLabel("��    �ţ�");  
        jl2=new JLabel("��    �룺");  
        //����Panel
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();
        //���ò��ֹ�����  
        this.setLayout(new GridLayout(4,2,5,5));
        //������  
        jp2.add(jl1);  
        jp2.add(jtf); 
        jp3.add(jl2);   
        jp3.add(jpwd);  
        jp4.add(jb1);   
        jp4.add(jb2); 
        //���Panel  
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3); 
        this.add(jp4); 
        //���ü�����
        jb1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		try {
					if(LoginAndRegister.search(jtf.getText(), jpwd.getText())){  //�ж��û����Ƿ���ȷ��������Ϸ����
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
					LoginAndRegister.add(jtf.getText(), jpwd.getText());//����û���Ϣ���ı��ļ���
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
        
        });
    }  
}



 