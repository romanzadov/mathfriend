package container.walks;

import display.rectangle;
import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;

public class ResetContainer  implements TreeFunction{

	public ResetContainer(term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(term tr) {
		tr.container = new rectangle();
	}
	
	
}
