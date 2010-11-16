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
