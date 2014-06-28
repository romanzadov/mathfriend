package move;

import java.util.ArrayList;

import move.identify.TermMath;
import tree.compound.CompoundTerm;

public class ChangeBranch {

	public CompoundTerm Change(CompoundTerm tree, CompoundTerm current, CompoundTerm future){
/*		CompoundTerm second = new CompoundTerm();
		try {
			second = (CompoundTerm) tree.clone();
		} catch (CloneNotSupportedException e) {
		}
		
	
		ArrayList<Integer> key = TermMath.findTreePositionOfSelected(tree, current);
		CompoundTerm moveto = TermMath.findTermUsingKey(second, key);
		
		future.setParent(moveto.getParent());
		int moveint = moveto.getParent().getChildren().indexOf(moveto);
		moveto.getParent().getChildren().set(moveint, future);
		
		return second;*/
        return null;
	}
}
