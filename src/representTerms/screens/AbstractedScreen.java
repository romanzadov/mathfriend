package representTerms.screens;

import java.util.ArrayList;

import representTerms.Images;
import representTerms.TouchData;
import representTerms.StringRectangle;
import representTerms.Settings.ScreenType;

public interface AbstractedScreen {
	
	public int getBackgroundColor();
	public ArrayList<StringRectangle> getRelativeRectangles();
	public void updateTouch(ArrayList<TouchData> touch);
	public void updateAbstractRectangles(ArrayList<StringRectangle> drawn);
	public void updateLogic();
	public void updateMainImage(Images main);
	public ScreenType getScreenType();
	
}
