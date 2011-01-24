package representTerms.screens;

import java.util.ArrayList;

import representTerms.Image;
import representTerms.TouchData;
import representTerms.stringrect;
import representTerms.Settings.ScreenType;

public class HistoryScreen implements AbstractedScreen{

	ArrayList<String> equations = new ArrayList<String>();
	
	@Override
	public int getBackgroundColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<stringrect> getRelativeRectangles() {
		
		ArrayList<stringrect> rects = new ArrayList<stringrect>();
		
		for(int i = 0; i<equations.size(); i++){
			Image img = new Image(equations.get(i), 2,2,2);
			rects.addAll(img.getRelativeContainers());
		}
		
		return rects;
	}

	@Override
	public ScreenType getScreenType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAbstractRectangles(ArrayList<stringrect> drawn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLogic() {
		equations.clear();
		equations.add("2x-4=6");
		equations.add("3x-5=7");
	}

	@Override
	public void updateMainImage(Image main) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTouch(ArrayList<TouchData> touch) {
		// TODO Auto-generated method stub
		
	}

}
