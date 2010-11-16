package move;

import java.util.ArrayList;

import move.identify.FindSel;
import move.identify.ReturnSel;
import tree.term;

public class ChangeBranch {

	public term Change(term tree, term current, term future){
		term second = new term();
		try {
			second = (term) tree.clone();
		} catch (CloneNotSupportedException e) {
		}
		
		FindSel fs = new FindSel();
		ArrayList<Integer> key = fs.FindSelected(tree, current);
		
		ReturnSel rs = new ReturnSel();
		term moveto = rs.Return(second, key);
		
		future.parent = moveto.parent;
		int moveint = moveto.parent.getChilds().indexOf(moveto);
		moveto.parent.getChilds().set(moveint, future);
		
		return second;
	}
}
