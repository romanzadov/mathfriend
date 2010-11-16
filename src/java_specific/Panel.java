package java_specific;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import representTerms.LogicEngine;
import representTerms.stringrect;

public class Panel extends JPanel{

	ArrayList <stringrect> toDraw; 
	LogicEngine logicEngine;


	public Panel(){
		logicEngine = RunAsJava.LOGIC_ENGINE;

		addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				mouseClicked(me);
			} 
		}); 
	}

	protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawString("hello", 50, 90);
        
    }

	public void mouseClicked(MouseEvent e)
	{
		if (e.getButton() == e.BUTTON1) // left mouse click
		{
			System.out.print("Left ");
		}
		else{
			System.out.print("Button Clicked\n");
		}
	} 
}
