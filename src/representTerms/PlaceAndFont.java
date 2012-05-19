package representTerms;

import display.Points;

public class PlaceAndFont {

	public Points bl;
	@Override
	public String toString() {
		return "PlaceAndFont [bl=" + bl + ", font=" + font + "]";
	}

	public float font;
	
	public PlaceAndFont(Points bel, float s){
		bl = bel;
		font = s;
	}
}
