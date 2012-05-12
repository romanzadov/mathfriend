package container.walks;

import tree.Term;
import tree.compound.CompoundTerm;
import tree.downwalk;
import tree.downwalk.TreeFunction;

public class resize  implements TreeFunction{

	
	public resize(Term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		/*tr.getContainer().height *= tr.getScaleFactor();
		tr.getContainer().width *= tr.getScaleFactor();
		tr.getContainer().bl.x *= tr.getScaleFactor();
		tr.getContainer().bl.y *= tr.getScaleFactor();*/
	}
	
	
}
