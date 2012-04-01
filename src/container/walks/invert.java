package container.walks;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;

public class invert  implements TreeFunction{

	
	public invert(Term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		tr.getContainer().bl.y*=-1;
	}
	

}
