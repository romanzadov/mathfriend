package tree.simple;

import tree.Term;
import display.rectangle;

import java.util.ArrayList;

public abstract class SimpleTerms extends Term{
	//has numbers, variables, and operators
	public String image;


	public rectangle giverect(Term tr){
		System.out.println("error: giverect was run on simpleterm");
		rectangle a = new rectangle();
		
		return a;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SimpleTerms clone = (SimpleTerms)super.clone();
		return clone;
	}

	@Override
    public ArrayList<Term> getChildren() {
        return new ArrayList<Term>();
    }
	
}
