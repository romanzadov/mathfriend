package representTerms;

import display.Rectangle;

public class StringRectangle implements Cloneable{

	@Override
	public String toString() {
		return "stringrect [container=" + container + ", fontscale="
				+ fontscale + ", hasParens=" + hasParens + ", myType=" + myType
				+ ", term=" + term + ", todraw=" + todraw + "]";
	}

	public Rectangle container = new Rectangle();
	public Type myType;
 	public String todraw = "";
	public float fontscale;
	public boolean hasParens = false;
	public enum Type {MAIN, SELECTED, GHOST, HISTORY, SELECT_COVER, FRACTION}
	public String term =  new String();


    public StringRectangle(){}
    public StringRectangle(Rectangle container, String string) {
        this.todraw = string;
        this.container = container;
    }

	@Override
	public Object clone() throws CloneNotSupportedException {
		StringRectangle clone = (StringRectangle)super.clone();
		clone.container = (Rectangle)this.container.clone();
		clone.term = (String)this.term;
		return clone;
	}
}
