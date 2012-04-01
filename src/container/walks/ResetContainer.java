package container.walks;

import display.rectangle;
import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;

public class ResetContainer  implements TreeFunction{

	public ResetContainer(Term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		tr.setContainer(new rectangle());
	}
	
	
}
