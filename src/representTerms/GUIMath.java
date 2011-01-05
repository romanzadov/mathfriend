package representTerms;

import java.util.ArrayList;

import display.point;
import display.rectangle;

public class GUIMath {
	
	
	public static rectangle scaleFloat(rectangle a, float f){
		rectangle b = new rectangle();
		b.height = a.height*f;
		b.width = a.width*f;
		b.bl.x = a.bl.x*f;
		b.bl.y = a.bl.y*f;
		return b;
	}

	public static rectangle translateXY(rectangle a, float x, float y){
		rectangle b = new rectangle();
		b.height = a.height;
		b.width = a.width;
		b.bl.x = a.bl.x+x;
		b.bl.y = a.bl.y+y;
		return b;
	}

	public static rectangle flipYVals(rectangle a){
		rectangle b = new rectangle();
		b.height = a.height;
		b.width = a.width;
		b.bl.x = a.bl.x;
		b.bl.y = a.bl.y + a.height;
		return b;
	}
	
	public static PlaceAndFont getCenteredPlaceAndFont(float xTerm, float yTerm, float prefferedFont, int xDisplay, int yDisplay){

		point bl = new point();
		
		if(justFits(xTerm, yTerm, prefferedFont, xDisplay, yDisplay)){
			bl = scaledBl(xTerm, yTerm, prefferedFont, xDisplay, yDisplay);
			return new PlaceAndFont(bl, prefferedFont);
		}
		else {
			int reducedFont = reducedFont(xTerm, yTerm, xDisplay, yDisplay);
			if(reducedFont > Settings.MIN_FONT){
				bl = scaledBl(xTerm, yTerm, reducedFont, xDisplay, yDisplay);
				return new PlaceAndFont(bl, reducedFont);
			}
			
			else{
				bl = scaledBl(xTerm, yTerm, Settings.MIN_FONT, xDisplay, yDisplay);
				return new PlaceAndFont(bl, Settings.MIN_FONT);
			}
		}
	}
	
	private static point scaledBl(float xTerm, float yTerm, float font, int xDisplay, int yDisplay){
		float xNew = (int) (xTerm*font);
		float yNew = (int) (yTerm*font);
		
		float xDisplayMiddle = xDisplay/2;
		float yDisplayMiddle = yDisplay/2;
		
		float xPos = xDisplayMiddle - xNew/2;
		float yPos = yDisplayMiddle - yNew/2;
		
		return new point(xPos, yPos);
	}
	
	private static int reducedFont(float xTerm, float yTerm, int xDisplay, int yDisplay){ 
		int xFontMinimum = (int) ((xDisplay - Settings.MIN_FONT - Settings.MIN_FONT)/(xTerm));
		int yFontMinimum = (int) ((yDisplay - Settings.MIN_FONT - Settings.MIN_FONT)/(yTerm));
		
		return Math.min(xFontMinimum, yFontMinimum);
	}
	
	private static boolean justFits(float xTerm, float yTerm, float prefferedFont, int xDisplay, int yDisplay){
		boolean fits = true;
		if((prefferedFont * xTerm) > (xDisplay-Settings.MIN_FONT-Settings.MIN_FONT)){fits = false;}
		if((prefferedFont * yTerm) > (yDisplay - Settings.MIN_FONT - Settings.MIN_FONT)){fits = false;}
		return fits;
	}
	
	
}
