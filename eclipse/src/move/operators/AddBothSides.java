package move.operators;

import tree.compound.CompoundTerm;
import tree.functions.Function;

public class AddBothSides {

	public CompoundTerm AddX(CompoundTerm tr, CompoundTerm sel, Function op){
		CompoundTerm second = null;
		
		try {
			second = (CompoundTerm) tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		/*if(op instanceof Minus){
			sel = sel.toggleNegative();
		}
		else if(op instanceof Plus){
			
			//add the number to both sides.
			
			



		
		else{
			second = null;
			System.out.println("You can only use plus or minus as an operator for AddBothSides. The operator used was :"+op);
		}*/
		return second;
	}
	
}
