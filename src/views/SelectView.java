package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SelectView extends JFrame{
	 private JPanel contentPane;

	  public SelectView() {
	    try {
	      jbInit();
	    }
	    catch(Exception ex) {
	      ex.printStackTrace();
	    }
	  }
	  
	  void jbInit() throws Exception {
	    contentPane = (JPanel)this.getContentPane();
	    contentPane.setLayout(new GridLayout(8,3));
	    JButton[] jb=new JButton[21];
	    for(int i=0;i<21;i++){
	    	jb[i]=new JButton("Level "+(i+1));
	    }
	    for(int i = 0; i<21; i++){
	       contentPane.add(jb[i]);
	    }
	    for(int i = 0; i<21; i++){
	    	jb[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {	
				
				}
	    	});
	    }
	    contentPane.add(new JButton("Privious"));
	    contentPane.add(new JButton("Back"));
	    contentPane.add(new JButton("Next"));
	    setSize(500,400);
	    setVisible(true);
	    
	  }
	  public int Rlevel(JButton jb){
		  return 0;
		  
	  }
	  public static void main(String args[]){
		  SelectView demo = new SelectView();
	      demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
	
}
