package container.walks;

import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;

public class invert  implements TreeFunction{

	
	public invert(term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(term tr) {
		tr.container.bl.y*=-1;
	}
	

}
