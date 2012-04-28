package tree.simple;

import tree.Term;
import display.rectangle;

public class Variables extends SimpleTerms {
	
	public Character value;
	
	public Variables(Character a){
		value = a;
	}
	
	@Override
	public String toString(){
		return value.toString();
	}
	
	@Override
	public Variables clone(){
		Variables v = new Variables(this.value);
		v.getContainer().bl.x = this.getContainer().bl.x;
		v.getContainer().bl.y = this.getContainer().bl.y;
		return v;
	}

	public rectangle giverect(Term tr){
		rectangle a = new rectangle();
		a.height = 1;
		a.width = 1;
		tr.setContainer(a);
		return a;
	}
	
	
	public boolean isoperator(){
		boolean a = false;
		return a;
	}
}
