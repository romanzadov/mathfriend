package tree.simple;

import tree.Term;
import display.rectangle;

public class Variable extends SimpleTerm {
	
	public String value;
	
	public Variable(String a){
		value = a;
	}
	
	public void setvalue(String a){
		value = a;
	}
	public Variable(){
	lmult = true;
	rmult = true;
	}
	
	@Override
	public String toString(){
		return value;
	}
	
	@Override
	public Variable clone(){
		Variable v = new Variable(this.value);
		v.getContainer().bl.x = this.getContainer().bl.x;
		v.getContainer().bl.y = this.getContainer().bl.y;
		return v;
	}

	public rectangle giverect(Term tr){
		rectangle a = new rectangle();
		tr.toDraw = value;
		a.height = 1;
		a.width = value.length();
		tr.setContainer(a);
		return a;
	}
	
	
	public boolean isoperator(){
		boolean a = false;
		return a;
	}
}
