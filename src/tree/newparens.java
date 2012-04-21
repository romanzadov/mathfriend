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
		if(tr.getParent().getFunction() instanceof Times|| tr.getParent().getFunction() instanceof Negative){
			
			if(tr.getFunction() instanceof Plus|| tr.getFunction() instanceof Minus){
					tr.setHasParentheses(true);
			}
		}
		//parentheses around exponents
		else if(tr.getParent().getFunction() instanceof Exponent){
			if(!tr.getParent().getChildren().get(0).issimple){
				tr.getParent().getChildren().get(0).setHasParentheses(true);
			}
		}
		//parentheses around subtraction
		else if(tr.getParent().getFunction() instanceof Plus || tr.getParent().getFunction() instanceof Minus){
			if(tr.getFunction() instanceof Plus || tr.getFunction() instanceof Minus){
				if(!tr.issimple){
                    tr.setHasParentheses(true);}
			}
		}
		
	}

}
