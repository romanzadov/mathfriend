package representTerms.screens;

import java.util.ArrayList;

import representTerms.Image;
import representTerms.TouchData;
import representTerms.StringRectangle;
import representTerms.Settings.ScreenType;
import representTerms.StringRectangle.Type;


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
	public ArrayList<StringRectangle> getRelativeRectangles() {

		ArrayList<StringRectangle> relatives = new ArrayList<StringRectangle>();

		if(ghost != null){
			relatives = ghost.getRelativeContainers();

		}
		
		//set the fraction rectangles to be identified as such
		for(int i = 0; i<relatives.size(); i++){

			StringRectangle a = relatives.get(i);
			if(a.todraw.equals("/")){
				a.todraw = "";
				a.myType = Type.FRACTION;
			}

		}

		return relatives;
	}

	@Override
	public void updateAbstractRectangles(ArrayList<StringRectangle> drawn) {

	}

	@Override
	public void updateLogic() {}

	@Override
	public void updateTouch(ArrayList<TouchData> touch) {}

}
