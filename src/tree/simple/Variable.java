package tree.simple;

import representTerms.StringRectangle;
import display.Rectangle;

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
    public StringRectangle getStringRectangle() {
        Rectangle container = new Rectangle();
		container.height = 1;
		container.width = 1;
		return new StringRectangle(container, value.toString());
    }

    @Override
	public Variable clone(){
		Variable v = new Variable(this.value);
		v.getContainer().bl.x = this.getContainer().bl.x;
		v.getContainer().bl.y = this.getContainer().bl.y;
		return v;
	}

}
