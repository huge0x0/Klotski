package views;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	JButton jb;  
    JTextField jtf;  
    JPasswordField jpwd;  
    JLabel jl1,jl2;  
    JPanel jp1,jp2,jp3,jp4;  
   
    public LoginView()  
    {   
    	//创建组件  
        jb=new JButton("登录");  
        jtf=new JTextField(10);  //账号
        jpwd=new JPasswordField(10);  //密码
        String user=jtf.getText();
        String pword=jpwd.getText();
        jpwd.setEchoChar('*');  
        jl1=new JLabel("账    号：");  
        jl2=new JLabel("密    码：");  
        
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
        jp4.add(jb);   
          
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3); 
        this.add(jp4); 
        //设置窗体属性  
        jb.addActionListener(new ActionListener(){
        	   public void actionPerformed(ActionEvent e)
        	   {
        		   
        	   }
        });
    }  
}

 