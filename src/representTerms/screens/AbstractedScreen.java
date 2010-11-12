package representTerms.screens;

import java.util.ArrayList;

import representTerms.stringrect;

public interface AbstractedScreen {

	public void updateMouse(int x, int y, boolean down);
	
	public ArrayList<stringrect> getThingsToDraw();
	
}
