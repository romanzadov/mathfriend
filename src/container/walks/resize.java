package container.walks;

import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;

public class resize  implements TreeFunction{

	
	public resize(term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(term tr) {
		tr.container.height *=tr.scalefactor;
		tr.container.width *=tr.scalefactor;
		tr.container.bl.x *=tr.scalefactor;
		tr.container.bl.y *=tr.scalefactor;
	}
	
	
}
