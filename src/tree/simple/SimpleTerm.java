package tree.simple;

import tree.CompoundTerm;
import display.rectangle;
import tree.Term;

import java.util.ArrayList;

public abstract class SimpleTerm extends Term {
	//has numbers, variables, and operators
	public String image;
    private String valueString;

    public String getValueString() {
        return valueString;
    }

    public rectangle giverect(CompoundTerm tr){
		System.out.println("error: giverect was run on simpleterm");
		rectangle a = new rectangle();
		
		return a;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SimpleTerm clone = (SimpleTerm)super.clone();
		return clone;
	}

    public boolean isRealNumber(){
		boolean real = false;
	/*	if(this.isRationalNumber()){
			real = true;
		}
		else*/ if(this instanceof Constants){
			real = true;
		}
		else if(this instanceof Number){
			real = true;
		}

		return real;
	}

}
