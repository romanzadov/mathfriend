package ghosts;

import java.util.ArrayList;

import container.RelativeContainer;
import display.point;

import move.identify.TermMath;
import representTerms.Image;
import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
import tree.operators.Times;

public class MultiplyGhost implements TreeFunction{

	Term a = null;
	Term b = null;
	Term aTIMESb = null;
	Image im;

	public Image Like(){
		
		Image Ghost =null;
		Term second = null;
		if(aTIMESb != null){
			try {
				second = (Term) im.tr.clone();
			} catch (CloneNotSupportedException e) {}

			ArrayList<Integer> key = TermMath.findTreePositionOfSelected(im.tr, a);
			Term as = TermMath.findTermUsingKey(second, key);
			key = TermMath.findTreePositionOfSelected(im.tr, b);
			Term bs = TermMath.findTermUsingKey(second, key);

			int smallest = Integer.MAX_VALUE;

			int ap = as.parent.getChilds().indexOf(as);
			int bp = bs.parent.getChilds().indexOf(bs);
			
			smallest = Math.min(ap, bp);
			int biggest = Math.max(ap, bp);
			
			if(as.parent.getChilds().size()==3){
				int place = as.parent.parent.getChilds().indexOf(as.parent);
				as.parent.parent.getChilds().set(place, aTIMESb);
				aTIMESb.parent = as.parent.parent;
			}
			else{
				as.parent.getChilds().remove(biggest-1);
				as.parent.getChilds().remove(biggest-1);
				as.parent.getChilds().set(smallest, aTIMESb);
				aTIMESb.parent = as.parent;
			}
			
			RelativeContainer dc = new RelativeContainer();
			dc.drawelement(second);
			Ghost = new Image(second, new point(im.bel.x, 80), null);
		}
		
		
		return Ghost;
	}
	
	public MultiplyGhost(Image img){
		im = img;
		downwalk walk = new downwalk(img.tr, this);
	}


	public void performAction(Term tr) {
		if(aTIMESb == null){
			if(tr.operator !=null && (tr.operator instanceof Times)){

				Term ans = null;
				boolean stop = false;
				for(int i = 0; i<tr.getChilds().size()-1; i++){
					for(int j = i+1; j<tr.getChilds().size(); j++){
						

						if(!stop){
						ans = (tr.getChilds().get(i)).MultiplyLikeTerms(tr.getChilds().get(j));
						}
						if(ans != null && !stop){
							
							aTIMESb = ans;
							a = tr.getChilds().get(i);
							b = tr.getChilds().get(j);
							stop  = true;
						
						}
						
					}
				}
			}
		}



	}

}
