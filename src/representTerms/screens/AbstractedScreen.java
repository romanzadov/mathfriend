package representTerms.screens;

import java.util.ArrayList;

import representTerms.Image;
import representTerms.TouchData;
import representTerms.stringrect;
import representTerms.Settings.ScreenType;

public interface AbstractedScreen {
	
	public int getBackgroundColor();
	public ArrayList<stringrect> getRelativeRectangles();
	public void updateTouch(ArrayList<TouchData> touch);
	public void updateAbstractRectangles(ArrayList<stringrect> drawn);
	public void updateLogic();
	public void updateMainImage(Image main);
	public ScreenType getScreenType();
	
}
