package controllers;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Play
{
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				new Game();
			}
		});
	}
}

class Chess extends JPanel
{
	Bars bar = new Aim(0);
	Bars[] bar1 = new Vertical_short[3];
	Bars[] bar2 = new Vertical_long[1];
	Bars[] bar3 = new Parallel_short[2];
	Bars[] bar4 = new Parallel_long[1];
    JButton left,right,above,below;
	static int square=50;
	Chess()
	{
		setLayout(null);
		setSize(310,310);
		setLocation(45,45);
		initialize();
	}
	public void initialize()
    {
    	MyListener m = new MyListener();
        
        bar.addMouseListener(m);
        bar.addMouseMotionListener(m);
        this.add(bar);
        for(int i=0;i<3;i++)
        {
            bar1[i]=new Vertical_short(1+i/10);
            bar1[i].addMouseListener(m);
            bar1[i].addMouseMotionListener(m);
            this.add(bar1[i]);
        }
        for(int i=0;i<1;i++)
        {
            bar2[i]=new Vertical_long(2+i/10);
            bar2[i].addMouseListener(m);
            bar2[i].addMouseMotionListener(m);
            this.add(bar2[i]);
        }
        for(int i=0;i<2;i++)
        {
            bar3[i]=new Parallel_short(3+i/10);
            bar3[i].addMouseListener(m);
            bar3[i].addMouseMotionListener(m);
            this.add(bar3[i]);
        }
        for(int i=0;i<1;i++)
        {
            bar4[i]=new Parallel_long(4+i/10);
            bar4[i].addMouseListener(m);
            bar4[i].addMouseMotionListener(m);
            this.add(bar4[i]);
        }
        //设定移动的板块
        bar.setLocation(5+square,5+square*2);
        bar1[0].setLocation(5,5+square*2);
        bar1[1].setLocation(5+square,5+square*3);
        bar1[2].setLocation(5+square*4,5+square*3);
        bar2[0].setLocation(5+square*3,5+square);
        bar3[0].setLocation(5+square*2,5+square*4);
        bar3[1].setLocation(5+square*4,5+square);
        bar4[0].setLocation(5+square*2,5);
        bar.requestFocus();   //将焦点放在控件上面
        //设定游戏边框
        left=new JButton();
        right=new JButton();
        above=new JButton();
        below = new JButton();
        this.add(left);
        this.add(right);
        this.add(above);
        this.add(below);
        left.setBounds(0,0,5,5+square*6+5);
        right.setBounds(5+square*6,0,5,5+square*6+5);
        above.setBounds(0,0,5+square*6+5,5);
        below.setBounds(0,5+square*6,5+square*6+5,5);
        
        validate(); //确保布局有效...这条语句不要也不会对程序有太大的影响
    }
	class MyListener extends MouseAdapter
    {
    	int newX,newY,oldX,oldY;
    	int startX,startY;
    	public void mousePressed(MouseEvent e)
        {
    		Bars Bar = (Bars)e.getSource();
    		startX = Bar.getX();
    		startY = Bar.getY();
    		oldX = e.getXOnScreen();
    		oldY = e.getYOnScreen();
        }
    	public void mouseDragged(MouseEvent e)
    	{
    		Bars Bar = (Bars)e.getSource();
    		if(bar.getX()==(5+square*4))
    		   	JOptionPane.showMessageDialog(new Chess(), "恭喜闯关", "游戏完成",JOptionPane.WARNING_MESSAGE);
    		//Rectangle barRect=Bar.getBounds();
    		boolean move_left=true;
    		boolean move_right=true;
    		boolean move_up=true;
    		boolean move_down=true;
    		//拖动的时候记录新坐标
    		newX = e.getXOnScreen();
    		newY = e.getYOnScreen();
            int x=startX+(newX - oldX);
            int y=startY+(newY - oldY);
            
            Rectangle barX1=Bar.getBounds();
            barX1.setLocation(Bar.getX()-1,Bar.getY());
            Rectangle barX2=Bar.getBounds();
            barX2.setLocation(Bar.getX()+1,Bar.getY());
            Rectangle barY1=Bar.getBounds();
            barY1.setLocation(Bar.getX(),Bar.getY()-1);
            Rectangle barY2=Bar.getBounds();
            barY2.setLocation(Bar.getX(),Bar.getY()+1);
            
            {
            	Rectangle boundary=left.getBounds();
            	if(barX1.intersects(boundary))
            		move_left=false;
            	if(barX2.intersects(boundary))
            		move_right=false;
            	if(barY1.intersects(boundary))
            		move_up=false;
            	if(barY2.intersects(boundary))
            		move_down=false;
            }
            
            {
            	Rectangle boundary=right.getBounds();
            	if(barX1.intersects(boundary))
            		move_left=false;
            	if(barX2.intersects(boundary))
            		move_right=false;
            	if(barY1.intersects(boundary))
            		move_up=false;
            	if(barY2.intersects(boundary))
            		move_down=false;
            }
            
            {
            	Rectangle boundary=above.getBounds();
            	if(barX1.intersects(boundary))
            		move_left=false;
            	if(barX2.intersects(boundary))
            		move_right=false;
            	if(barY1.intersects(boundary))
            		move_up=false;
            	if(barY2.intersects(boundary))
            		move_down=false;
            }
            
            {
            	Rectangle boundary=below.getBounds();
            	if(barX1.intersects(boundary))
            		move_left=false;
            	if(barX2.intersects(boundary))
            		move_right=false;
            	if(barY1.intersects(boundary))
            		move_up=false;
            	if(barY2.intersects(boundary))
            		move_down=false;
            }
            
            {
            	Rectangle barsRect=bar.getBounds();
            	if(barX1.intersects(barsRect) && Bar.num!=0)
            		move_left=false;
            	if(barX2.intersects(barsRect) && Bar.num!=0)
            		move_right=false;
            	if(barY1.intersects(barsRect) && Bar.num!=0)
            		move_up=false;
            	if(barY2.intersects(barsRect) && Bar.num!=0)
            		move_down=false;
            }
            
            for(int k=0;k<3;k++)
            {
                Rectangle barsRect=bar1[k].getBounds();
                if(barX1.intersects(barsRect) && Bar.num!=1+k/10)
                    move_left=false;
                if(barX2.intersects(barsRect) && Bar.num!=1+k/10)
                    move_right=false;
                if(barY1.intersects(barsRect) && Bar.num!=1+k/10)
                    move_up=false;
                if(barY2.intersects(barsRect) && Bar.num!=1+k/10)
                    move_down=false;
            }
            
            for(int k=0;k<1;k++)
            {
                Rectangle barsRect=bar2[k].getBounds();
                if(barX1.intersects(barsRect) && Bar.num!=2+k/10)
                    move_left=false;
                if(barX2.intersects(barsRect) && Bar.num!=2+k/10)
                    move_right=false;
                if(barY1.intersects(barsRect) && Bar.num!=2+k/10)
                    move_up=false;
                if(barY2.intersects(barsRect) && Bar.num!=2+k/10)
                    move_down=false;
            }
            
            for(int k=0;k<2;k++)
            {
                Rectangle barsRect=bar3[k].getBounds();
                if(barX1.intersects(barsRect) && Bar.num!=3+k/10)
                    move_left=false;
                if(barX2.intersects(barsRect) && Bar.num!=3+k/10)
                    move_right=false;
                if(barY1.intersects(barsRect) && Bar.num!=3+k/10)
                    move_up=false;
                if(barY2.intersects(barsRect) && Bar.num!=3+k/10)
                    move_down=false;
            }
            
            for(int k=0;k<1;k++)
            {
                Rectangle barsRect=bar4[k].getBounds();
                if(barX1.intersects(barsRect) && Bar.num!=4+k/10)
                    move_left=false;
                if(barX2.intersects(barsRect) && Bar.num!=4+k/10)
                    move_right=false;
                if(barY1.intersects(barsRect) && Bar.num!=4+k/10)
                    move_up=false;
                if(barY2.intersects(barsRect) && Bar.num!=4+k/10)
                    move_down=false;
            }
         
            //设置bounds,将点下时记录的组件开始坐标与鼠标拖动的距离相加
    		if((e.getSource() instanceof Aim || e.getSource() instanceof Parallel_short || e.getSource() instanceof Parallel_long) && move_left==true && (newX-oldX)<0)
    		{
    			Bar.setLocation(startX+(newX - oldX), startY);
    		}
    		if((e.getSource() instanceof Aim || e.getSource() instanceof Parallel_short || e.getSource() instanceof Parallel_long) && move_right==true && (newX-oldX)>0)
    		{
    			Bar.setLocation(startX+(newX - oldX), startY);
    		}
    		if((e.getSource() instanceof Vertical_short || e.getSource() instanceof Vertical_long) && move_up==true && (newY - oldY)<0)
    		{
    			Bar.setLocation(startX, startY+(newY - oldY));
    		}
    		if((e.getSource() instanceof Vertical_short || e.getSource() instanceof Vertical_long) && move_down==true && (newY - oldY)>0)
    		{
    			Bar.setLocation(startX, startY+(newY - oldY));
    		}
    	}
    }
}

class Game extends JFrame implements MouseListener,KeyListener,ActionListener
{
	
	
	static int locationX=500,locationY=200;
    public Game()
    {
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setLayout(null);
    	setResizable(false);
    	setBounds(locationX,locationY,410,580);  //设置窗口初始位置以及大小的一个函数
    	setVisible(true);
    	JButton restart=new JButton("重新开始");
    	restart.addActionListener(this);
    	restart.setBounds(150,500,90,30);
    	this.add(restart);
    	this.add(new Chess());  //生成一个棋盘
    	validate();  //使用 validate方法会使容器再次布置其子组件。确保布局有效。
    }
    public void actionPerformed(ActionEvent e)
    {
        dispose();  //注销
        new Game();
    }
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void mousePressed(MouseEvent e) {}   
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}