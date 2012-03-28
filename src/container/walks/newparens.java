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
		if(tr.parent.operator instanceof Times||tr.parent.operator instanceof Negative){
			
			if(tr.operator instanceof Plus||tr.operator instanceof Minus){
					tr.hasparen = true;
			}
		}
		//parentheses around exponents
		else if(tr.parent.operator instanceof Exponent){
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
		
//		Log.d(TAG, "tr: "+tr.toString()+"  parens: "+tr.hasparen);
	}
	

}
