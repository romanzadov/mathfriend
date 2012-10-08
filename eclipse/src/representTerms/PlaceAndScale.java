package representTerms;

import display.Point;

public class PlaceAndScale {

	public Point bl;
	public double scale;
	
	public PlaceAndScale(Point bel, double s){
		bl = bel;
		scale = s;
	}
	public PlaceAndScale(Point bel, float s){
		bl = bel;
		scale = s;
	}
}
