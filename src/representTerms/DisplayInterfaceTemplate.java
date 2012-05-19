package representTerms;

import java.util.ArrayList;

import display.Point;
import display.Rectangle;

public abstract class DisplayInterfaceTemplate implements DisplayInterface{

	
	ArrayList<StringRectangle> rectanglesScaledToIdentity = new ArrayList<StringRectangle>();
	ArrayList<TouchData> touches  = new ArrayList<TouchData>();
	
	@Override
	public ArrayList<StringRectangle> getDrawnRectanglesScaledToIdentity(){
		return rectanglesScaledToIdentity;
	}


	public ArrayList<StringRectangle> center(ArrayList<StringRectangle> toDraw){
		
		rectanglesScaledToIdentity = new ArrayList<StringRectangle>();
		Rectangle first = toDraw.get(0).container;
		PlaceAndFont paf = GUIMath.getCenteredPlaceAndFont(first.width, first.height, Settings.PREFFERED_FONT, getWidth(), getHeight());
	//	System.out.println( " "+paf);
		for(int i = 0; i< toDraw.size(); i++){
			Rectangle a = toDraw.get(i).container;
			a.scaleAndTranslate(paf.font);
			a.translate(paf.bl.x, paf.bl.y);
			a.flipY(getHeight());
			
			
			StringRectangle b;
			try {
				b = (StringRectangle) toDraw.get(i).clone();
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
	public Point scaleToIdealScreen(Point a) {
		Point b = new Point(a.x/getWidth() , (getHeight()-a.y)/getHeight());
		return b;
	}


	@Override
	public Point scaleToRealScreen(Point a) {
		Point b = new Point(a.x*getWidth() , a.y*getHeight());
		return b;
	}


	@Override
	public Rectangle scaleToIdealScreen(Rectangle a) {
		Rectangle b = new Rectangle();
		b.height = a.height/getHeight();
		b.width = a.width/getWidth();
		b.bl.x = a.bl.x/getWidth();
		b.bl.y = (getHeight()-a.bl.y-a.height)/getHeight();
		return b;
	}


	@Override
	public Rectangle scaleToRealScreen(Rectangle a) {
		Rectangle b = new Rectangle();
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
	public abstract void updateDrawnRectangles(ArrayList<StringRectangle> toDraw);


	@Override
	public void addTouchEvent(TouchData touch) {
		touches.add(touch);
	}

}
