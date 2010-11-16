package representTerms;

import java.util.ArrayList;

import display.point;
import display.rectangle;

public interface DisplayInterface {

	public int getWidth();
	public int getHeight();
	
	public boolean setBackgroundColor(int color);		//pass in int RGB
	public void Paint(ArrayList<stringrect> toDraw);
	
	public point scaleToIdealScreen(point a);
	public point scaleToRealScreen(point a);
	
	public rectangle scaleToIdealScreen(rectangle a);
	public rectangle scaleToRealScreen(rectangle a);
	
	public void passTouchEvent( TouchData touch);  //shoud also make sure to scale it
}
