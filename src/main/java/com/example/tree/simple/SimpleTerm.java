package com.example.tree.simple;

import com.example.parse.PreSimpleTerm;
import com.example.tree.Term;

public abstract class SimpleTerm extends Term {
	//has numbers, variables, and operators
	public String image;


    public static SimpleTerm getSimpleTerm(PreSimpleTerm preSimpleTerm) {
        SimpleTerm child = null;

        if (PreSimpleTerm.Type.CONSTANT.equals(preSimpleTerm.getType())) {
            child = new Constants(preSimpleTerm.getConstant());
        } else if (PreSimpleTerm.Type.NUMBER.equals(preSimpleTerm.getType())) {
            Double value = Double.parseDouble(preSimpleTerm.toString());
            child = new com.example.tree.simple.Number(value);
        } else if (PreSimpleTerm.Type.VARIABLE.equals(preSimpleTerm.getType())) {
            child = new Variable(preSimpleTerm.getCharacters().get(0));
        }
        return child;
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
