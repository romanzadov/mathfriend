package container.walks;

import tree.Term;
import tree.downwalk;
import tree.downwalk.TreeFunction;

public class fontize  implements TreeFunction{

	double font;
	
	public fontize(Term tr, double fontsize){
		
		if(tr.getContainer().getWidth() *fontsize>270){
			fontsize = (int)270/ tr.getContainer().getWidth();
		}
		
		
		font = fontsize;
		downwalk walk = new downwalk(tr, this);
		
	}

	public void performAction(Term tr) {

		tr.getContainer().setHeight(tr.getContainer().getHeight() * font);
		tr.getContainer().setWidth(tr.getContainer().getWidth() * font);
		tr.getContainer().bl.x *=font;
		tr.getContainer().bl.y *=font;
	//	tr.font = font;
	}
	
	


}
