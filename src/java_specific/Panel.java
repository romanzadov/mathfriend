package java_specific;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import representTerms.DisplayInterface;
import representTerms.LogicEngine;
import representTerms.Settings;
import representTerms.TouchData;
import representTerms.stringrect;
import representTerms.TouchData.TouchType;
import representTerms.stringrect.type;
import display.rectangle;

public class Panel extends JPanel implements MouseMotionListener, MouseListener{

	ArrayList <stringrect> toDraw = new ArrayList<stringrect>(); 

	TouchData lastTouch;
	boolean mousePressed = false;
	DisplayInterface myDisplay;
	
	
	public Panel(DisplayInterface disp){
		myDisplay = disp;
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	public void setToDraw(ArrayList<stringrect> todraw){
		toDraw = todraw;
	}

	protected void paintComponent(Graphics g)
	{
		
		g.setColor(Color.LIGHT_GRAY);			//these two lines are only here because I can't get "repaint" to work correctly.
		g.fillRect(0,0 , getWidth(), getHeight());
		g.setColor(Color.black);
		
		if(toDraw != null){
			for(int i = 0;  i<toDraw.size(); i++){
				stringrect a = toDraw.get(i);
				rectangle c = a.container;
				
				g.setColor(Color.white);

				if(toDraw.get(i).myType == type.SELECT_COVER){
					g.setColor(this.getBackground());
					g.fillRect((int)c.bl.x,(int)c.bl.y, (int)c.width, (int)c.height);
					g.setColor(Color.black);
					
				}
				
				if(toDraw.get(i).myType == type.SELECTED){
					g.fillRect((int)c.bl.x,(int)c.bl.y, (int)c.width, (int)c.height);
				}
				g.setColor(Color.black);


				g.drawRect((int)c.bl.x,(int)c.bl.y, (int)c.width, (int)c.height);

			



				if(!a.todraw.equals("")){
					g.drawString(a.todraw, (int)(c.bl.x+c.width/4), (int)(c.topleft().y-c.height/8));
				}


			}
		}

		for(int i = 0; i< 50; i++){
	//		g.drawString(""+i*10, 10, i*10);
	//		g.drawString(""+i*50, i*50, 10);
		}
	}

	public void updateToDraw(ArrayList<stringrect> td){
		toDraw = td;
	}

	@Override
	public void mouseClicked(MouseEvent e){

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override 
	public void mouseDragged(MouseEvent e){
		lastTouch = new TouchData(e.getX(), e.getY(), mousePressed , System.currentTimeMillis(), TouchType.DRAGGED);
		myDisplay.addTouchEvent(lastTouch);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastTouch = new TouchData(e.getX(), e.getY(), mousePressed ,  System.currentTimeMillis(), TouchType.PRESSED);
		myDisplay.addTouchEvent(lastTouch);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		lastTouch = new TouchData(e.getX(), e.getY(), mousePressed ,  System.currentTimeMillis(), TouchType.RELEASED);
		myDisplay.addTouchEvent(lastTouch);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	} 
}
