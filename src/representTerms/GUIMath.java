package representTerms;

import java.util.ArrayList;

import display.point;

public class GUIMath {

	public static int MIN_FONT = 0;
	public static int MAX_FONT = 40;
	int xDisplay;
	int yDisplay;
	
	public void updateScreen(int xDisp, int yDisp){
		xDisplay = xDisp;
		yDisplay = yDisp;
	}
	
	public GUIMath(int minF, int maxF, int displayWidth, int displayHeight){
		MIN_FONT = minF;
		MAX_FONT = maxF;
		xDisplay = displayWidth;
		yDisplay = displayHeight;
	}
	
	public PlaceAndFont getCenteredPlaceAndFont(float xTerm, float yTerm, int prefferedFont){

		point bl = new point();
		
		if(justFits(xTerm, yTerm, prefferedFont)){
			bl = scaledBl(xTerm, yTerm, prefferedFont);
			return new PlaceAndFont(bl, prefferedFont);
		}
		else {
			int reducedFont = reducedFont(xTerm, yTerm);
			if(reducedFont > MIN_FONT){
				bl = scaledBl(xTerm, yTerm, reducedFont);
				return new PlaceAndFont(bl, reducedFont);
			}
			
			else{
				bl = scaledBl(xTerm, yTerm, MIN_FONT);
				return new PlaceAndFont(bl, MIN_FONT);
			}
		}
	}
	
	private point scaledBl(float xTerm, float yTerm, int font){
		int xNew = (int) (xTerm*font);
		int yNew = (int) (yTerm*font);
		
		int xDisplayMiddle = xDisplay/2;
		int yDisplayMiddle = yDisplay/2;
		
		int xPos = xDisplayMiddle - xNew;
		int yPos = yDisplayMiddle + yNew;
		
		return new point(xPos, yPos);
	}
	
	private int reducedFont(float xTerm, float yTerm){ 
		int xFontMinimum = (int) ((xDisplay - MIN_FONT - MIN_FONT)/(xTerm));
		int yFontMinimum = (int) ((yDisplay - MIN_FONT - MIN_FONT)/(yTerm));
		
		return Math.min(xFontMinimum, yFontMinimum);
	}
	
	private boolean justFits(float xTerm, float yTerm, int prefferedFont){
		boolean fits = true;
		if((prefferedFont * xTerm) > (xDisplay-MIN_FONT-MIN_FONT)){fits = false;}
		if((prefferedFont * yTerm) > (yDisplay - MIN_FONT - MIN_FONT)){fits = false;}
		return fits;
	}
	
}
