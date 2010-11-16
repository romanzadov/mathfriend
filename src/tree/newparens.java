package tree;

import tree.operators.exponent;
import tree.operators.minus;
import tree.operators.negative;
import tree.operators.parens;
import tree.operators.plus;
import tree.operators.times;
import tree.downwalk.TreeFunction;

public class newparens implements TreeFunction {

	public newparens(term tr){
		downwalk walk = new downwalk(tr, this);
	}
	
	public void performAction(term tr) {
	
		//parentheses around multiplied terms
		if(tr.parent.operator instanceof times||tr.parent.operator instanceof negative){
			
			if(tr.operator instanceof plus||tr.operator instanceof minus){
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
		else if(tr.parent.operator instanceof plus || tr.parent.operator instanceof minus){
			if(tr.operator instanceof plus || tr.operator instanceof minus){
				if(!tr.issimple){tr.hasparen = true;}
			}
		}
		
	}

}
