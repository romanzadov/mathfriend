package container.walks;

import tree.downwalk;
import tree.CompoundTerm;
import tree.downwalk.TreeFunction;
//import android.util.Log;

public class newparens implements TreeFunction {

	final static String TAG = "newparens";
	
	public newparens(CompoundTerm tr){
		downwalk walk = new downwalk(tr, this);
	}
	
	public void performAction(CompoundTerm tr) {
		
	/*	//parentheses around multiplied terms
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
		}*/
		
//		Log.d(TAG, "tr: "+tr.toString()+"  parens: "+tr.hasparen);
	}
	

}
