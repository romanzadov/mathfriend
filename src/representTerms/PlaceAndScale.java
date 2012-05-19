package representTerms;

import display.Points;

public class PlaceAndScale {

	public Points bl;
	public double scale;
	
	public PlaceAndScale(Points bel, double s){
		bl = bel;
		scale = s;
	}
	public PlaceAndScale(Points bel, float s){
		bl = bel;
		scale = s;
	}
}
