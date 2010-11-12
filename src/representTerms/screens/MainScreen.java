package representTerms.screens;

import java.util.ArrayList;

import representTerms.Image;
import representTerms.stringrect;

public class MainScreen implements AbstractedScreen{
	
	int width;
	int height;
	
	Image main;
	Image sel;
	
	public MainScreen(int w, int h){
		width = w;
		height = h;
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
