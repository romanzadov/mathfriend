package container.walks;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;

public class fontize  implements TreeFunction{

	double font;
	
	public fontize(Term tr, double fontsize){
		
		if(tr.container.width*fontsize>270){
			fontsize = (int)270/tr.container.width;
		}
		
		
		font = fontsize;
		downwalk walk = new downwalk(tr, this);
		
	}

	public void performAction(Term tr) {

		tr.container.height *=font;
		tr.container.width *=font;
		tr.container.bl.x *=font;
		tr.container.bl.y *=font;
		tr.font = font;
	}
	
	


}
