package move;

import java.util.ArrayList;

import move.identify.TermMath;
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
		
		future.setParent(moveto.getParent());
		int moveint = moveto.getParent().getChildren().indexOf(moveto);
		moveto.getParent().getChildren().set(moveint, future);
		
		return second;
	}
}
