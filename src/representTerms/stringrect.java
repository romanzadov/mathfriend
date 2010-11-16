package representTerms;

import display.point;
import display.rectangle;

public class stringrect implements Cloneable{

	public String term =  new String();
	public String todraw = "";
	public rectangle container = new rectangle();
	public float fontscale;
	public boolean hasParens = false;

	@Override
	public Object clone() throws CloneNotSupportedException {
		stringrect clone = (stringrect)super.clone();
		clone.container = (rectangle)this.container.clone();
		clone.term = (String)this.term;
		return clone;
	}
}
