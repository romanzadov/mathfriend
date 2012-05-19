package representTerms;

import java.util.ArrayList;

import representTerms.screens.AbstractedScreen;

import display.Points;
import display.Rectangles;

public interface DisplayInterface {

	public abstract int getWidth();
	public abstract int getHeight();
	
	abstract boolean setBackgroundColor(int color);		//pass in int RGB
	public void updateDrawnRectangles(ArrayList<StringRectangle> toDraw);
	
	public Points scaleToIdealScreen(Points a);
	public Points scaleToRealScreen(Points a);
	
	public Rectangles scaleToIdealScreen(Rectangles a);
	public Rectangles scaleToRealScreen(Rectangles a);
	
	public ArrayList<StringRectangle> getDrawnRectanglesScaledToIdentity();
	public AbstractedScreen getAbstractScreen();
	
	public ArrayList <TouchData> getTouchData();  //shoud also make sure to scale it
	public void addTouchEvent(TouchData touch);
	public void setSize(int x, int y);
}
