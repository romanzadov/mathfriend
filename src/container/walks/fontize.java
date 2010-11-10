package container.walks;

import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;

public class fontize  implements TreeFunction{

	double font;
	
	public fontize(term tr, double fontsize){
		
		if(tr.container.width*fontsize>270){
			fontsize = (int)270/tr.container.width;
		}
		
		
		font = fontsize;
		downwalk walk = new downwalk(tr, this);
		
	}

	public void performAction(term tr) {

		tr.container.height *=font;
		tr.container.width *=font;
		tr.container.bl.x *=font;
		tr.container.bl.y *=font;
		tr.font = font;
	}
	
	


}
