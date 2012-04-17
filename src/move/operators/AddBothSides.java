package move.operators;

import tree.Term;
import tree.functions.Minus;
import tree.functions.Function;
import tree.functions.Plus;

public class AddBothSides {

	public Term AddX(Term tr, Term sel, Function op){
		Term second = null;
		
		try {
			second = (Term) tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		if(op instanceof Minus){
			sel = sel.toggleNegative();
		}
		else if(op instanceof Plus){
			
			//add the number to both sides.
			
			
		}
		
		
		
		
		else{
			second = null;
			System.out.println("You can only use plus or minus as an operator for AddBothSides. The operator used was :"+op);
		}
		return second;
	}
	
}
