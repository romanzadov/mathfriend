package representTerms;

import display.Point;

public class PlaceAndFont {

	public Point bl;
	@Override
	public String toString() {
		return "PlaceAndFont [bl=" + bl + ", font=" + font + "]";
	}

	public float font;
	
	public PlaceAndFont(Point bel, float s){
		bl = bel;
		font = s;
	}
}
