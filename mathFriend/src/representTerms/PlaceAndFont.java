package representTerms;

import display.point;

public class PlaceAndFont {

	public point bl;
	@Override
	public String toString() {
		return "PlaceAndFont [bl=" + bl + ", font=" + font + "]";
	}

	public float font;
	
	public PlaceAndFont(point bel, float s){
		bl = bel;
		font = s;
	}
}
