package tree.simple;

import tree.term;
import display.rectangle;

public class variable extends simpleterm{
	
	public String value;
	
	public variable(String a){
		value = a;
	}
	
	public void setvalue(String a){
		value = a;
	}
	public variable(){
	lmult = true;
	rmult = true;
	}
	
	@Override
	public String toString(){
		return value;
	}
	
	@Override
	public variable clone(){
		variable v = new variable(this.value);
		v.font = this.font;
		v.container.bl.x = this.container.bl.x;
		v.container.bl.y = this.container.bl.y;
		return v;
	}

	public rectangle giverect(term tr){
		rectangle a = new rectangle();
		tr.todraw = value;
		a.height = 1;
		a.width = value.length();
		tr.container = a;
		return a;
	}
	
	
	public boolean isoperator(){
		boolean a = false;
		return a;
	}
}
