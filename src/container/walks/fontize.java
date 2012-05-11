package container.walks;

import tree.downwalk;
import tree.CompoundTerm;
import tree.downwalk.TreeFunction;

public class fontize  implements TreeFunction{

	double font;
	
	public fontize(CompoundTerm tr, double fontsize){
		
		if(tr.getContainer().width*fontsize>270){
			fontsize = (int)270/ tr.getContainer().width;
		}
		
		
		font = fontsize;
		downwalk walk = new downwalk(tr, this);
		
	}

	public void performAction(CompoundTerm tr) {

		tr.getContainer().height *=font;
		tr.getContainer().width *=font;
		tr.getContainer().bl.x *=font;
		tr.getContainer().bl.y *=font;
	//	tr.font = font;
	}
	
	


}
