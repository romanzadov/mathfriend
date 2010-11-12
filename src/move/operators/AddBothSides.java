package move.operators;

import tree.term;
import tree.operators.minus;
import tree.operators.operator;
import tree.operators.plus;

public class AddBothSides {

	public term AddX(term tr, term sel, operator op){
		term second = null;
		
		try {
			second = (term) tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		if(op instanceof minus){
			sel = sel.toggleNegative();
		}
		else if(op instanceof plus){
			
			//add the number to both sides.
			
			
		}
		
		
		
		
		else{
			second = null;
			System.out.println("You can only use plus or minus as an operator for AddBothSides. The operator used was :"+op);
		}
		return second;
	}
	
}
