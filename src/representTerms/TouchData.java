package representTerms;

public class TouchData {

	float x;
	float y;
	float pressure;
	boolean down;
	
	public TouchData(float X, float Y, boolean d){
		x = X;
		y = Y;
		down = d;
	}
	
	public TouchData(float X, float Y, boolean d, float p){
		x = X;
		y = Y;
		pressure = p;
		down = d;
	}
	
}
