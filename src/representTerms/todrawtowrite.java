package representTerms;

import java.util.ArrayList;

import display.Rectangle;

public class todrawtowrite implements Cloneable{
	

	public ArrayList<Rectangle> todraw = new ArrayList<Rectangle>();
	public ArrayList<StringRectangle> towrite = new ArrayList<StringRectangle>();

	@Override
	public Object clone() throws CloneNotSupportedException {
		todrawtowrite clone = (todrawtowrite)super.clone();
		clone.todraw = new ArrayList<Rectangle>();
		clone.towrite = new ArrayList<StringRectangle>();
		for(int i = 0; i<this.todraw.size(); i++){
			clone.todraw.add((Rectangle)this.todraw.get(i).clone());
		}
		for(int i = 0; i<this.towrite.size(); i++){
			clone.towrite.add((StringRectangle)this.towrite.get(i).clone());
		}
		return clone;
	}

}
