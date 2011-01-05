package representTerms;

import java.util.ArrayList;

import representTerms.screens.AbstractedScreen;
import display.point;
import display.rectangle;

public abstract class DisplayInterfaceTemplate implements DisplayInterface{

	
	ArrayList<stringrect> rectanglesScaledToIdentity = new ArrayList<stringrect>();
	ArrayList<TouchData> touches  = new ArrayList<TouchData>();
	
	@Override
	public ArrayList<stringrect> getDrawnRectanglesScaledToIdentity(){
		return rectanglesScaledToIdentity;
	}


	public ArrayList<stringrect> center(ArrayList<stringrect> toDraw){
		
		rectanglesScaledToIdentity = new ArrayList<stringrect>();
		rectangle first = toDraw.get(0).container;
		PlaceAndFont paf = GUIMath.getCenteredPlaceAndFont(first.width, first.height, Settings.PREFFERED_FONT, getWidth(), getHeight());
	//	System.out.println( " "+paf);
		for(int i = 0; i< toDraw.size(); i++){
			rectangle a = toDraw.get(i).container;
			a.scaleAndTranslate(paf.font);
			a.translate(paf.bl.x, paf.bl.y);
			a.flipY(getHeight());
			
			
			stringrect b;
			try {
				b = (stringrect) toDraw.get(i).clone();
				b.container = scaleToIdealScreen(a);
				rectanglesScaledToIdentity.add(b);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return toDraw;
	}

	@Override
	public point scaleToIdealScreen(point a) {
		point b = new point(a.x/getWidth() , (getHeight()-a.y)/getHeight());
		return b;
	}


	@Override
	public point scaleToRealScreen(point a) {
		point b = new point(a.x*getWidth() , a.y*getHeight());
		return b;
	}


	@Override
	public rectangle scaleToIdealScreen(rectangle a) {
		rectangle b = new rectangle();
		b.height = a.height/getHeight();
		b.width = a.width/getWidth();
		b.bl.x = a.bl.x/getWidth();
		b.bl.y = (getHeight()-a.bl.y-a.height)/getHeight();
		return b;
	}


	@Override
	public rectangle scaleToRealScreen(rectangle a) {
		rectangle b = new rectangle();
		b.height = a.height*getHeight();
		b.width = a.width*getWidth();
		b.bl.x = a.bl.x*getWidth();
		b.bl.y = a.bl.y*getHeight();
		return b;
	}

	


	@Override
	public ArrayList<TouchData> getTouchData() {
		//take the list of stored touch events, scale them to the ideal screen, and return them. 
		//Also, removing each of them from the list of touch events so far.
		
		ArrayList<TouchData> scaledData = new ArrayList<TouchData>();
		int length = touches.size();
		for(int i = 0; i<length; i++){
			TouchData touch = touches.get(0);
			touch.position = scaleToIdealScreen(touch.position);
			scaledData.add(touch);
			touches.remove(0);
		}

			return scaledData;
	}

	



	@Override
	public abstract int getHeight();



	@Override
	public abstract int getWidth();



	@Override
	public abstract boolean setBackgroundColor(int color);

	@Override
	public abstract void updateDrawnRectangles(ArrayList<stringrect> toDraw);


	@Override
	public void addTouchEvent(TouchData touch) {
		touches.add(touch);
	}

}
