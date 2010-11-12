package representTerms.screens;

import java.util.ArrayList;

import representTerms.Image;
import representTerms.stringrect;
import user.UserPrefferences;


public class GhostScreen implements AbstractedScreen{

	public int width;
	public int height;
	
	Image Ghost;
	
	public GhostScreen(int w, int h){
		width = w;
		height = h;
	}
	
	public void updateGhost(String newGhost){
		Ghost = new Image(newGhost, UserPrefferences.PREFFERED_FONT, width, height);
	}

	@Override
	public void updateMouse(int x, int y, boolean down) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<stringrect> getThingsToDraw() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
