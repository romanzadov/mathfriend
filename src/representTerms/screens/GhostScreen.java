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
	

	@Override
	public int getBackgroundColor() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public ArrayList<stringrect> getRelativeRectangles() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void changeEquation() {
		// TODO Auto-generated method stub
		
	}
	
}
