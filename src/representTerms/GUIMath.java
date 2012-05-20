package representTerms;

import display.Point;
import display.Rectangle;

public class GUIMath {
	
	public GUIMath(float st, float fontSize, float screenWidth, float screenHeight){

    }

	public static Rectangle scaleFloat(Rectangle a, float f){
		Rectangle b = new Rectangle();
		b.setHeight(a.getHeight() *f);
		b.setWidth(a.getWidth() *f);
		b.bl.x = a.bl.x*f;
		b.bl.y = a.bl.y*f;
		return b;
	}

	public static Rectangle translateXY(Rectangle a, float x, float y){
		Rectangle b = new Rectangle();
		b.setHeight(a.getHeight());
		b.setWidth(a.getWidth());
		b.bl.x = a.bl.x+x;
		b.bl.y = a.bl.y+y;
		return b;
	}

	public static Rectangle flipYVals(Rectangle a){
		Rectangle b = new Rectangle();
		b.setHeight(a.getHeight());
		b.setWidth(a.getWidth());
		b.bl.x = a.bl.x;
		b.bl.y = a.bl.y + a.getHeight();
		return b;
	}
	
	public static PlaceAndFont getCenteredPlaceAndFont(float xTerm, float yTerm, float prefferedFont, int xDisplay, int yDisplay){

		Point bl = new Point();
		
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
	
	private static Point scaledBl(float xTerm, float yTerm, float font, int xDisplay, int yDisplay){
		float xNew = (int) (xTerm*font);
		float yNew = (int) (yTerm*font);
		
		float xDisplayMiddle = xDisplay/2;
		float yDisplayMiddle = yDisplay/2;
		
		float xPos = xDisplayMiddle - xNew/2;
		float yPos = yDisplayMiddle - yNew/2;
		
		return new Point(xPos, yPos);
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
