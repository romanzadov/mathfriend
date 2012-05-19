package representTerms;

import java.util.ArrayList;

import display.Rectangles;

public class todrawtowrite implements Cloneable{
	

	public ArrayList<Rectangles> todraw = new ArrayList<Rectangles>();
	public ArrayList<StringRectangle> towrite = new ArrayList<StringRectangle>();

	@Override
	public Object clone() throws CloneNotSupportedException {
		todrawtowrite clone = (todrawtowrite)super.clone();
		clone.todraw = new ArrayList<Rectangles>();
		clone.towrite = new ArrayList<StringRectangle>();
		for(int i = 0; i<this.todraw.size(); i++){
			clone.todraw.add((Rectangles)this.todraw.get(i).clone());
		}
		for(int i = 0; i<this.towrite.size(); i++){
			clone.towrite.add((StringRectangle)this.towrite.get(i).clone());
		}
		return clone;
	}

}
