package tree.simple;

import representTerms.StringRectangle;
import display.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Variable extends SimpleTerm {
	
	public Character value;
	
	public Variable(Character a){
		value = a;
	}
	
	@Override
	public String toString(){
		return value.toString();
	}

    @Override
    public List<StringRectangle> getRelativeRectangles() {
        List<StringRectangle> rectangles = new ArrayList<StringRectangle>();
        rectangles.add(new StringRectangle(getContainer(), toString()));
        return rectangles;
    }

    @Override
    public Rectangle getContainer() {
		return new Rectangle(1,1);
    }

    @Override
	public Variable clone(){
		Variable v = new Variable(this.value);
		v.getContainer().bl.x = this.getContainer().bl.x;
		v.getContainer().bl.y = this.getContainer().bl.y;
		return v;
	}

}
