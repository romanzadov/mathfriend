package tree;

import tree.operators.exponent;
import tree.operators.Minus;
import tree.operators.negative;
import tree.operators.parens;
import tree.operators.Plus;
import tree.operators.times;
import tree.downwalk.TreeFunction;

public class newparens implements TreeFunction {

	public newparens(Term tr){
		downwalk walk = new downwalk(tr, this);
	}
	
	public void performAction(Term tr) {
	
		//parentheses around multiplied terms
		if(tr.parent.operator instanceof times||tr.parent.operator instanceof negative){
			
			if(tr.operator instanceof Plus||tr.operator instanceof Minus){
					tr.hasparen = true;
			}
		}
		//parentheses around exponents
		else if(tr.parent.operator instanceof exponent){
			if(!tr.parent.getChilds().get(0).issimple){
				tr.parent.getChilds().get(0).hasparen = true;
			}
		}
		//parentheses around subtraction
		else if(tr.parent.operator instanceof Plus || tr.parent.operator instanceof Minus){
			if(tr.operator instanceof Plus || tr.operator instanceof Minus){
				if(!tr.issimple){tr.hasparen = true;}
			}
		}
		
	}

}
