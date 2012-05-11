package container.walks;

import tree.downwalk;
import tree.CompoundTerm;
import tree.downwalk.TreeFunction;

public class invert  implements TreeFunction{

	
	public invert(CompoundTerm tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(CompoundTerm tr) {
		tr.getContainer().bl.y*=-1;
	}
	

}
