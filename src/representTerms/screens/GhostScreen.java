package representTerms.screens;

import java.awt.Color;
import java.util.ArrayList;

import representTerms.Image;
import representTerms.TouchData;
import representTerms.stringrect;
import representTerms.Settings.ScreenType;
import user.UserPrefferences;


public class GhostScreen implements AbstractedScreen{

	final static int backgroundColor = Color.lightGray.getRGB();
	public Image ghost;
	
	
	@Override 
	public ScreenType getScreenType(){
		return ScreenType.GHOST;
	}
	
	@Override
	public void updateMainImage(Image main) {
		ghost = main;
	}
	
	@Override
	public int getBackgroundColor() {
		return backgroundColor;
	}

	@Override
	public ArrayList<stringrect> getRelativeRectangles() {
		
		ArrayList<stringrect> relatives = new ArrayList<stringrect>();
		
		if(ghost != null){
			relatives = ghost.getRelativeContainers();

		}
		
		return relatives;
	}

	@Override
	public void updateAbstractRectangles(ArrayList<stringrect> drawn) {
		
	}

	@Override
	public void updateLogic() {}

	@Override
	public void updateTouch(ArrayList<TouchData> touch) {}
	
}
