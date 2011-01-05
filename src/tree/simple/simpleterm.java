package tree.simple;

import tree.Term;
import tree.notsimple.NotSimple;
import display.rectangle;

public class simpleterm extends NotSimple{
	//has numbers, variables, and operators
	

	public simpleterm(){
		issimple = true;
		
	}
	
	public boolean lmult;
	public boolean rmult;
	
	public String image;
	
	public rectangle giverect(Term tr){
		System.out.println("error: giverect was run on simpleterm");
		rectangle a = new rectangle();
		
		return a;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		simpleterm clone = (simpleterm)super.clone();
		return clone;
	}

	@Override
	public boolean isNegative(Term tr) {
		// TODO Auto-generated method stub
		return false;
	}
}
