package representTerms.screens;

import java.awt.Color;
import java.util.ArrayList;

import representTerms.Image;
import representTerms.TouchData;
import representTerms.stringrect;
import representTerms.Settings.ScreenType;
import representTerms.stringrect.type;
import user.UserPrefferences;


public class GhostScreen implements AbstractedScreen{


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
		return 0;
	}

	@Override
	public ArrayList<stringrect> getRelativeRectangles() {

		ArrayList<stringrect> relatives = new ArrayList<stringrect>();

		if(ghost != null){
			relatives = ghost.getRelativeContainers();

		}
		
		//set the fraction rectangles to be identified as such
		for(int i = 0; i<relatives.size(); i++){

			stringrect a = relatives.get(i);
			if(a.todraw.equals("/")){
				a.todraw = "";
				a.myType = type.FRACTION;
			}

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
