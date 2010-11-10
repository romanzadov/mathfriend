package representTerms;

import display.point;

public class GUIMath {

	int minFont;
	int maxFont;
	int xDisplay;
	int yDisplay;
	
	public GUIMath(int minF, int maxF, int displayWidth, int displayHeight){
		minFont = minF;
		maxFont = maxF;
		xDisplay = displayWidth;
		yDisplay = displayHeight;
	}
	
	
	public PlaceAndFont getPlaceAndFont(float xTerm, float yTerm, int prefferedFont){

		point bl = new point();
		
		System.out.println("x: "+xTerm+" y: "+yTerm+ " font: "+prefferedFont + " xd: "+xDisplay+ " yd: "+yDisplay);
		
		if(justFits(xTerm, yTerm, prefferedFont)){
			bl = scaledBl(xTerm, yTerm, prefferedFont);
			return new PlaceAndFont(bl, prefferedFont);
		}
		else {
			int reducedFont = reducedFont(xTerm, yTerm);
			if(reducedFont > minFont){
				bl = scaledBl(xTerm, yTerm, reducedFont);
				return new PlaceAndFont(bl, reducedFont);
			}
			
			else{
				bl = scaledBl(xTerm, yTerm, minFont);
				return new PlaceAndFont(bl, minFont);
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
		int xFontMinimum = (int) ((xDisplay - minFont - minFont)/(xTerm));
		int yFontMinimum = (int) ((yDisplay - minFont - minFont)/(yTerm));
		
		return Math.min(xFontMinimum, yFontMinimum);
	}
	
	private boolean justFits(float xTerm, float yTerm, int prefferedFont){
		boolean fits = true;
		if((prefferedFont * xTerm) > (xDisplay-minFont-minFont)){fits = false;}
		if((prefferedFont * yTerm) > (yDisplay - minFont - minFont)){fits = false;}
		return fits;
	}
	
	
}
