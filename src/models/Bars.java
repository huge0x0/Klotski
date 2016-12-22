package models;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JPanel;

abstract public class Bars extends JPanel
{
	double num;
    Bars(double num)
    {
    	this.num=num;
    }
}

class Aim extends Bars
{
	Aim(double d)
	{
		super(d);
        setBackground(Color.red);
        setSize(100,50);
	}
}

class Vertical_short extends Bars
{
	Vertical_short(double s)
	{
		super(s);
        setBackground(Color.orange);
        setSize(50,100);
	}
}

class Vertical_long extends Bars
{
	Vertical_long(double s)
	{
		super(s);
        setBackground(Color.blue);
        setSize(50,150);
	}
}

class Parallel_short extends Bars
{
	Parallel_short(double s)
	{
		super(s);
        setBackground(Color.orange);
        setSize(100,50);
	}
}

class Parallel_long extends Bars
{
	Parallel_long(double s)
	{
		super(s);
        setBackground(Color.blue);
        setSize(150,50);
	}
}