package tree.simple;

import tree.Term;
import display.rectangle;

import java.util.ArrayList;

public abstract class SimpleTerm extends Term{
	//has numbers, variables, and operators
	public String image;
    private String valueString;

    public String getValueString() {
        return valueString;
    }

    public rectangle giverect(Term tr){
		System.out.println("error: giverect was run on simpleterm");
		rectangle a = new rectangle();
		
		return a;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SimpleTerm clone = (SimpleTerm)super.clone();
		return clone;
	}

	@Override
    public ArrayList<Term> getChildren() {
        return new ArrayList<Term>();
    }
	
}
