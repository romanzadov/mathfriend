package container.walks;

import tree.Term;
import tree.compound.CompoundTerm;
import tree.downwalk;
import tree.downwalk.TreeFunction;

public class invert  implements TreeFunction{

	
	public invert(Term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		tr.getContainer().bl.y*=-1;
	}
	

}