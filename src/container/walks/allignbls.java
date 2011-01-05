package container.walks;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;

public class allignbls  implements TreeFunction{

	static final String TAG = "allignbls";
	
	public allignbls(Term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		try {
			tr.container.bl.x +=tr.parent.container.bl.x;
			tr.container.bl.y +=tr.parent.container.bl.y;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//	System.out.println("Problem in allignbls: this term  "+tr.todraw+"  "+tr);
		}
	}
	
	
}
