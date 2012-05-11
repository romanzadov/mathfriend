package container.walks;

import tree.downwalk;
import tree.CompoundTerm;
import tree.downwalk.TreeFunction;

public class resize  implements TreeFunction{

	
	public resize(CompoundTerm tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(CompoundTerm tr) {
		tr.getContainer().height *= tr.getScaleFactor();
		tr.getContainer().width *= tr.getScaleFactor();
		tr.getContainer().bl.x *= tr.getScaleFactor();
		tr.getContainer().bl.y *= tr.getScaleFactor();
	}
	
	
}
