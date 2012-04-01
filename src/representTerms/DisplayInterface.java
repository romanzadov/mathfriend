package representTerms;

import java.util.ArrayList;

import representTerms.screens.AbstractedScreen;

import display.point;
import display.rectangle;

public interface DisplayInterface {

	public abstract int getWidth();
	public abstract int getHeight();
	
	abstract boolean setBackgroundColor(int color);		//pass in int RGB
	public void updateDrawnRectangles(ArrayList<StringRectangle> toDraw);
	
	public point scaleToIdealScreen(point a);
	public point scaleToRealScreen(point a);
	
	public rectangle scaleToIdealScreen(rectangle a);
	public rectangle scaleToRealScreen(rectangle a);
	
	public ArrayList<StringRectangle> getDrawnRectanglesScaledToIdentity();
	public AbstractedScreen getAbstractScreen();
	
	public ArrayList <TouchData> getTouchData();  //shoud also make sure to scale it
	public void addTouchEvent(TouchData touch);
	public void setSize(int x, int y);
}
