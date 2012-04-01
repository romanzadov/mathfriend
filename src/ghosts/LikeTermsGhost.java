package ghosts;

import java.util.ArrayList;

import move.identify.TermMath;
import representTerms.Image;
import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
import tree.operators.Minus;
import tree.operators.Plus;
import display.point;

public class LikeTermsGhost implements TreeFunction{

	Term a = null;
	Term b = null;
	Term aPLUSb = null;
	Image im;

	public Image Like(){
		Image Ghost =null;
		Term second = null;
		if(aPLUSb != null){
			try {
				second = (Term) im.tr.clone();
			} catch (CloneNotSupportedException e) {}

			
			ArrayList<Integer> key = TermMath.findTreePositionOfSelected(im.tr, a);
			
			Term as = TermMath.findTermUsingKey(second, key);
			key = TermMath.findTreePositionOfSelected(im.tr, b);
			Term bs = TermMath.findTermUsingKey(second, key);

			int smallest = Integer.MAX_VALUE;

			int ap = as.parent.getChildren().indexOf(as);
			int bp = bs.parent.getChildren().indexOf(bs);
			
			smallest = Math.min(ap, bp);
			int biggest = Math.max(ap, bp);
			
			as.parent.getChildren().remove(biggest-1);
			as.parent.getChildren().remove(biggest-1);
			if(smallest == 0){as.parent.getChildren().remove(smallest);}
			else{
				as.parent.getChildren().remove(smallest-1);
				as.parent.getChildren().remove(smallest-1);
			}
			
			
			if(smallest>0){smallest --;}
			
			aPLUSb.parent = as.parent;
			if(smallest == 0){
				as.parent.getChildren().add(0,aPLUSb);
			}
			else{
				if(!aPLUSb.isNegative()){
					Plus pl = new Plus();
					pl.parent = aPLUSb.parent;
					aPLUSb.parent.getChildren().add(smallest,aPLUSb);
					aPLUSb.parent.getChildren().add(smallest,pl);
				}
				else{
					Minus mn = new Minus();
					mn.parent = aPLUSb.parent;
					aPLUSb.parent.getChildren().add(smallest,aPLUSb);
					aPLUSb.parent.getChildren().add(smallest,mn);
					aPLUSb.toggleNegative();
				}
			}
		//	RelativeContainer dc = new RelativeContainer();
		//	dc.drawelement(second, (int)im.tr.font);
			Ghost = new Image(second, new point(im.bel.x, 80), null);
		}

		return Ghost;
	}
	public LikeTermsGhost(Image img){
		im = img;
		downwalk walk = new downwalk(img.tr, this);
	}

	public void performAction(Term tr) {
		if(aPLUSb == null){
			if(tr.operator !=null && (tr.operator instanceof Plus || tr.operator instanceof Minus)){

				Term ans = null;
				boolean stop = false;
				for(int i = 0; i<tr.getChildren().size()-1; i++){
					for(int j = i+1; j<tr.getChildren().size(); j++){
						
						if(!stop){
						ans = (tr.getChildren().get(i)).AddLikeTerms(tr.getChildren().get(j));
						}
						if(ans != null && !stop){
							
							aPLUSb = ans;
							a = tr.getChildren().get(i);
							b = tr.getChildren().get(j);
							stop  = true;
						
						}
					}
				}
			}
		}
	}



}
