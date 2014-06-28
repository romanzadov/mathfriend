package representTerms;

import display.rectangle;

public class StringRectangle implements Cloneable{

	@Override
	public String toString() {
		return "stringrect [container=" + container + ", fontscale="
				+ fontscale + ", hasParens=" + hasParens + ", myType=" + myType
				+ ", term=" + term + ", todraw=" + todraw + "]";
	}

	public rectangle container = new rectangle();
	public type myType;
	public String todraw = "";
	public float fontscale;
	public boolean hasParens = false;
	public enum type {MAIN, SELECTED, GHOST, HISTORY, SELECT_COVER, FRACTION}
	public String term =  new String();

	@Override
	public Object clone() throws CloneNotSupportedException {
		StringRectangle clone = (StringRectangle)super.clone();
		clone.container = (rectangle)this.container.clone();
		clone.term = (String)this.term;
		return clone;
	}
}
