package representTerms;

import java.util.ArrayList;

import representTerms.screens.AbstractedScreen;

import display.Point;
import display.Rectangle;

public interface DisplayInterface {

	public abstract int getWidth();
	public abstract int getHeight();
	
	abstract boolean setBackgroundColor(int color);		//pass in int RGB
	public void updateDrawnRectangles(ArrayList<StringRectangle> toDraw);
	
	public Point scaleToIdealScreen(Point a);
	public Point scaleToRealScreen(Point a);
	
	public Rectangle scaleToIdealScreen(Rectangle a);
	public Rectangle scaleToRealScreen(Rectangle a);
	
	public ArrayList<StringRectangle> getDrawnRectanglesScaledToIdentity();
	public AbstractedScreen getAbstractScreen();
	
	public ArrayList <TouchData> getTouchData();  //shoud also make sure to scale it
	public void addTouchEvent(TouchData touch);
	public void setSize(int x, int y);
}
