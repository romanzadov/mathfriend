package representTerms.screens;

import java.awt.Color;
import java.util.ArrayList;

import representTerms.Image;
import representTerms.Settings;
import representTerms.TouchData;
import representTerms.stringrect;
import representTerms.Settings.ScreenType;
import representTerms.TouchData.TouchType;
import tree.operators.Operator;
import user.UserPrefferences;


public class OperatorScreen implements AbstractedScreen{

	
	public Operator operator;
	Image sel;
	ArrayList<TouchData> touches = new ArrayList<TouchData>();
	int timesTapped = 0;
	
	@Override 
	public ScreenType getScreenType(){
		return ScreenType.OPERATOR;
	}
	
	
	@Override
	public void updateMainImage(Image main) {
		operator = main.tr.operator;
		sel = main;
		touches.clear();
	//	resetTimesTapped();
	}

	@Override
	public int getBackgroundColor() {
		return 0;
	}
	
	private Image getImage(){
		if(operator == null){return null;}
		else{
			Image img = new Image(operator.valuestring, Settings.PREFFERED_FONT, 1, 1);
			return img;
		}
	}

	@Override
	public ArrayList<stringrect> getRelativeRectangles() {
		
		ArrayList<stringrect> relatives = new ArrayList<stringrect>();
		if(getImage() != null){
			
			stringrect sr = new stringrect();
			sr.container.width = 1;
			sr.container.height = 1;
			sr.todraw = operator.valuestring;
			relatives.add(sr);
		}
		
		return relatives;
	}

	@Override
	public void updateAbstractRectangles(ArrayList<stringrect> drawn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTouch(ArrayList<TouchData> touch){
		touches.addAll(touch);
		updateLogic();
	}

	@Override
	public void updateLogic(){
		for(int i = 0; i<touches.size(); i++){
			singleTouch(touches.get(0));
			touches.remove(0);
		}
		
	
	}
	
	private void singleTouch(TouchData touch){
		
		
		
		if(touch.myType == TouchType.RELEASED){
			timesTapped++;
		}
	}
	
	public int getTimesTapped(){
		return timesTapped;
	}
	public void resetTimesTapped(){
		timesTapped = 0;
	}
	
}
