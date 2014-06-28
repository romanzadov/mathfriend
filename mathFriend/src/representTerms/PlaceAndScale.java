package representTerms;

import display.point;

public class PlaceAndScale {

	public point bl;
	public double scale;
	
	public PlaceAndScale(point bel, double s){
		bl = bel;
		scale = s;
	}
	public PlaceAndScale(point bel, float s){
		bl = bel;
		scale = s;
	}
}
