package container.walks;

import display.Rectangle;
import tree.Term;
import tree.downwalk;
import tree.downwalk.TreeFunction;

public class ResetContainer  implements TreeFunction{

	public ResetContainer(Term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		tr.setContainer(new Rectangle());
	}
	
	
}
