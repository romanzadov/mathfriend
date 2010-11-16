package representTerms.screens;

import java.util.ArrayList;

import representTerms.stringrect;

public interface AbstractedScreen {
	
	public int getBackgroundColor();
	public ArrayList<stringrect> getRelativeRectangles();
	public void changeEquation();
	
}
