package move;

import java.util.ArrayList;

import move.identify.TermMath;
import move.identify.ReturnSel;
import tree.Term;

public class ChangeBranch {

	public Term Change(Term tree, Term current, Term future){
		Term second = new Term();
		try {
			second = (Term) tree.clone();
		} catch (CloneNotSupportedException e) {
		}
		
	
		ArrayList<Integer> key = TermMath.findTreePositionOfSelected(tree, current);
		Term moveto = TermMath.findTermUsingKey(second, key);
		
		future.parent = moveto.parent;
		int moveint = moveto.parent.getChilds().indexOf(moveto);
		moveto.parent.getChilds().set(moveint, future);
		
		return second;
	}
}
