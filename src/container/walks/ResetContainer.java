package container.walks;

import display.rectangle;
import tree.downwalk;
import tree.CompoundTerm;
import tree.downwalk.TreeFunction;

public class ResetContainer  implements TreeFunction{

	public ResetContainer(CompoundTerm tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(CompoundTerm tr) {
		tr.setContainer(new rectangle());
	}
	
	
}
