package container.walks;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
import tree.operators.Exponent;
import tree.operators.Minus;
import tree.operators.Negative;
import tree.operators.Plus;
import tree.operators.Times;
//import android.util.Log;

public class newparens implements TreeFunction {

	final static String TAG = "newparens";
	
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
		
//		Log.d(TAG, "tr: "+tr.toString()+"  parens: "+tr.hasparen);
	}
	

}
