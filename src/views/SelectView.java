package views;

import java.awt.*;
import javax.swing.*;

public class SelectView extends JFrame{
	 private JPanel contentPane;
	 JButton jb[];
	 for(int i = 0; i<21; i++){
		 jb[i]=new JButton("Level "+(i+1)))
	}
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
	    for(int i = 0; i<21; i++){
	       contentPane.add(new JButton("Level "+(i+1)));
	    }
	    contentPane.add(new JButton("Privious"));
	    contentPane.add(new JButton("Back"));
	    contentPane.add(new JButton("Next"));
	    setSize(500,400);
	    setVisible(true);
	    
	  }
	  
	  public static void main(String args[]){
		  SelectView demo = new SelectView();
	      demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
	
}
