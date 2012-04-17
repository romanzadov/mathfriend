package tree;

import tree.functions.Exponent;
import tree.functions.Minus;
import tree.functions.Negative;
import tree.functions.Plus;
import tree.functions.Times;
import tree.downwalk.TreeFunction;

public class newparens implements TreeFunction {

	public newparens(Term tr){
		downwalk walk = new downwalk(tr, this);
	}
	
	public void performAction(Term tr) {
	
		//parentheses around multiplied terms
		if(tr.getParent().getOperator() instanceof Times|| tr.getParent().getOperator() instanceof Negative){
			
			if(tr.getOperator() instanceof Plus|| tr.getOperator() instanceof Minus){
					tr.setHasParentheses(true);
			}
		}
		//parentheses around exponents
		else if(tr.getParent().getOperator() instanceof Exponent){
			if(!tr.getParent().getChildren().get(0).issimple){
				tr.getParent().getChildren().get(0).setHasParentheses(true);
			}
		}
		//parentheses around subtraction
		else if(tr.getParent().getOperator() instanceof Plus || tr.getParent().getOperator() instanceof Minus){
			if(tr.getOperator() instanceof Plus || tr.getOperator() instanceof Minus){
				if(!tr.issimple){
                    tr.setHasParentheses(true);}
			}
		}
		
	}

}
