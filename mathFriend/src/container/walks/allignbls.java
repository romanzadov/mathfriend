package container.walks;

import tree.Term;
import tree.compound.CompoundTerm;
import tree.downwalk;
import tree.downwalk.TreeFunction;

public class allignbls  implements TreeFunction{

	static final String TAG = "allignbls";
	
	public allignbls(CompoundTerm tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		try {
			tr.getContainer().bl.x += tr.getParent().getContainer().bl.x;
			tr.getContainer().bl.y += tr.getParent().getContainer().bl.y;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//	System.out.println("Problem in allignbls: this term  "+tr.todraw+"  "+tr);
		}
	}
	
	
}