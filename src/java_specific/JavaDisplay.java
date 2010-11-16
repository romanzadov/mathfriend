package java_specific;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import display.point;
import display.rectangle;

import representTerms.DisplayInterface;
import representTerms.TouchData;
import representTerms.stringrect;
import representTerms.screens.AbstractedScreen;

public class JavaDisplay implements DisplayInterface {

	Panel myPanel;
	AbstractedScreen myScreen;

	public JavaDisplay(Panel panel, AbstractedScreen screen){
		myPanel = panel;
		myScreen = screen;
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
	public boolean setBackgroundColor(int color) {
		//pass in int RGB
		myPanel.setBackground(new Color(color));
		return false;
	}


	@Override
	public void Paint(ArrayList<stringrect> toDraw) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public point scaleToIdealScreen(point a) {
		point b = new point(a.x/getWidth() , a.y/getHeight());
		return b;
	}


	@Override
	public point scaleToRealScreen(point a) {
		point b = new point(a.x*getWidth() , a.y*getHeight());
		return b;
	}


	@Override
	public rectangle scaleToIdealScreen(rectangle a) {
		rectangle b = new rectangle();
		b.height = a.height/getHeight();
		b.width = a.width/getWidth();
		b.bl.x = a.bl.x/getWidth();
		b.bl.y = a.bl.y/getHeight();
		return b;
	}


	@Override
	public rectangle scaleToRealScreen(rectangle a) {
		rectangle b = new rectangle();
		b.height = a.height*getHeight();
		b.width = a.width*getWidth();
		b.bl.x = a.bl.x*getWidth();
		b.bl.y = a.bl.y*getHeight();
		return b;
	}


	@Override
	public void passTouchEvent(TouchData touch) {
		
	}

}
