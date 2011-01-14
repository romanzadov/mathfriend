package java_specific;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import representTerms.DisplayInterface;
import representTerms.DisplayInterfaceTemplate;
import representTerms.GUIMath;
import representTerms.LogicEngine;
import representTerms.PlaceAndFont;
import representTerms.Settings;
import representTerms.TouchData;
import representTerms.stringrect;
import representTerms.screens.AbstractedScreen;
import display.point;
import display.rectangle;

public class JavaDisplay extends DisplayInterfaceTemplate {

	static JFrame f;
	Panel myPanel;
	AbstractedScreen myScreen;
	ArrayList<TouchData> touches  = new ArrayList<TouchData>();
	
	
	
	public JavaDisplay(AbstractedScreen screen){
		Container cp = null;
		if(f == null){
			f = new JFrame();
			f.setSize(700, 700);
			cp = f.getContentPane();
			cp.setLayout(new GridLayout(3, 1));
		}
		
		myPanel = new Panel(this);
		myScreen = screen;
		f.add(myPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		setBackgroundColor(screen.getScreenType().color);
		setSize(myScreen.getScreenType().width, myScreen.getScreenType().height);
	}

	@Override
	public AbstractedScreen getAbstractScreen() {
		return myScreen;
	}
	
	
	@Override
	public int getWidth() {
		return myPanel.getWidth();
	}

	@Override
	public int getHeight() {
		return myPanel.getHeight();
	}

	@Override
	public void setSize(int x, int y){
		myPanel.setSize(x, y);
	//	f.setSize(x, y);
	}

	
	@Override
	public boolean setBackgroundColor(int color) {
		//pass in int RGB
		myPanel.setBackground(new Color(color));
		return false;
	}


	@Override
	public void updateDrawnRectangles(ArrayList<stringrect> toDraw) {
	
		if(toDraw.size()>0){
		rectangle a = toDraw.get(0).container;
		toDraw = center(toDraw);
		myPanel.updateToDraw(toDraw);
		myPanel.invalidate();
		myPanel.repaint();
		}
		
		
	}
	

}
