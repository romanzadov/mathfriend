package representTerms;

import java.util.ArrayList;

import display.rectangle;

public class todrawtowrite implements Cloneable{
	

	public ArrayList<rectangle> todraw = new ArrayList<rectangle>();
	public ArrayList<StringRectangle> towrite = new ArrayList<StringRectangle>();

	@Override
	public Object clone() throws CloneNotSupportedException {
		todrawtowrite clone = (todrawtowrite)super.clone();
		clone.todraw = new ArrayList<rectangle>();
		clone.towrite = new ArrayList<StringRectangle>();
		for(int i = 0; i<this.todraw.size(); i++){
			clone.todraw.add((rectangle)this.todraw.get(i).clone());
		}
		for(int i = 0; i<this.towrite.size(); i++){
			clone.towrite.add((StringRectangle)this.towrite.get(i).clone());
		}
		return clone;
	}

}
