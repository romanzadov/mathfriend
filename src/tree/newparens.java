package tree;

import tree.operators.Exponent;
import tree.operators.Minus;
import tree.operators.Negative;
import tree.operators.Plus;
import tree.operators.Times;
import tree.downwalk.TreeFunction;

public class newparens implements TreeFunction {

	public newparens(Term tr){
		downwalk walk = new downwalk(tr, this);
	}
	
	public void performAction(Term tr) {
	
		//parentheses around multiplied terms
		if(tr.parent.operator instanceof Times||tr.parent.operator instanceof Negative){
			
			if(tr.operator instanceof Plus||tr.operator instanceof Minus){
					tr.hasParentheses = true;
			}
		}
		//parentheses around exponents
		else if(tr.parent.operator instanceof Exponent){
			if(!tr.parent.getChildren().get(0).issimple){
				tr.parent.getChildren().get(0).hasParentheses = true;
			}
		}
		//parentheses around subtraction
		else if(tr.parent.operator instanceof Plus || tr.parent.operator instanceof Minus){
			if(tr.operator instanceof Plus || tr.operator instanceof Minus){
				if(!tr.issimple){tr.hasParentheses = true;}
			}
		}
		
	}

}
