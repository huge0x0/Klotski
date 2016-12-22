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
		 frame.setTitle("��¼");
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
					if(LoginandRegister.search(jtf.getText(), jpwd.getText())){  //�ж��û����Ƿ���ȷ��������Ϸ����
						System.out.println("OK");  //����ת���ӿ�
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        });
        jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					LoginandRegister.add(jtf.getText(), jpwd.getText());//����û���Ϣ���ı��ļ���
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
        
        });
    }  
}

class LoginandRegister{//��¼��ѯ
	static boolean search(String user,String pword)throws IOException{
		String[][] yonghu=new String[10][2];
		File file = new File("E:\\javafiles\\Klotski\\src\\views\\�û�����.txt");//����������ݵ��ļ�
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
	static void add(String user,String pword)throws IOException{  
        String temp="\r\n"+user+"\t"+pword;  
        String temp2=user+"\t"+pword;
        File file = new File("E:\\javafiles\\Klotski\\src\\views\\�û�����.txt");  
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

 