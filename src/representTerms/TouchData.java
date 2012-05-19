package representTerms;

import display.Point;

public class TouchData {

	public Point position;
	public float pressure;
	public boolean down;
	public long time;
	public enum TouchType {PRESSED, DRAGGED, RELEASED}
	public TouchType myType;
	
	public TouchData(float X, float Y, boolean d, long t, TouchType type){
		position = new Point(X, Y);
		down = d;
		time = t;
		myType = type;
	}
	
	public TouchData(float X, float Y, boolean d, float p, long t, TouchType type){
		position = new Point(X, Y);
		pressure = p;
		down = d;
		time = t;
		myType = type;
	}
	
	@Override
	public String toString(){
		return "("+position.x+","+position.y+")"+
					" down: "+down+" time: "+time+myType;
	}
	
}
