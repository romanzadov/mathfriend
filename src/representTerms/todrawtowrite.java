package representTerms;

import java.util.ArrayList;

import tree.Term;

import display.point;
import display.rectangle;

public class todrawtowrite implements Cloneable{
	

	public ArrayList<rectangle> todraw = new ArrayList<rectangle>();
	public ArrayList<stringrect> towrite = new ArrayList<stringrect>();

	@Override
	public Object clone() throws CloneNotSupportedException {
		todrawtowrite clone = (todrawtowrite)super.clone();
		clone.todraw = new ArrayList<rectangle>();
		clone.towrite = new ArrayList<stringrect>();
		for(int i = 0; i<this.todraw.size(); i++){
			clone.todraw.add((rectangle)this.todraw.get(i).clone());
		}
		for(int i = 0; i<this.towrite.size(); i++){
			clone.towrite.add((stringrect)this.towrite.get(i).clone());
		}
		return clone;
	}

}
