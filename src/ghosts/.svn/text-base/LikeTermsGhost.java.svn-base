package ghosts;

import java.util.ArrayList;

import container.RelativeContainer;

import move.identify.FindSel;
import move.identify.ReturnSel;
import representTerms.image;
import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;
import tree.operators.minus;
import tree.operators.plus;
import display.point;

public class LikeTermsGhost implements TreeFunction{

	term a = null;
	term b = null;
	term aPLUSb = null;
	image im;

	public image Like(){
		image Ghost =null;
		term second = null;
		if(aPLUSb != null){
			try {
				second = (term) im.tr.clone();
			} catch (CloneNotSupportedException e) {}

			FindSel fs = new FindSel();
			ArrayList<Integer> key = fs.FindSelected(im.tr, a);
			ReturnSel rs = new ReturnSel();
			term as = rs.Return(second, key);
			key = fs.FindSelected(im.tr, b);
			term bs = rs.Return(second, key);

			int smallest = Integer.MAX_VALUE;

			int ap = as.parent.getChilds().indexOf(as);
			int bp = bs.parent.getChilds().indexOf(bs);
			
			smallest = Math.min(ap, bp);
			int biggest = Math.max(ap, bp);
			
			as.parent.getChilds().remove(biggest-1);
			as.parent.getChilds().remove(biggest-1);
			if(smallest == 0){as.parent.getChilds().remove(smallest);}
			else{
				as.parent.getChilds().remove(smallest-1);
				as.parent.getChilds().remove(smallest-1);
			}
			
			
			if(smallest>0){smallest --;}
			
			aPLUSb.parent = as.parent;
			if(smallest == 0){
				as.parent.getChilds().add(0,aPLUSb);
			}
			else{
				if(!aPLUSb.isNegative()){
					plus pl = new plus();
					pl.parent = aPLUSb.parent;
					aPLUSb.parent.getChilds().add(smallest,aPLUSb);
					aPLUSb.parent.getChilds().add(smallest,pl);
				}
				else{
					minus mn = new minus();
					mn.parent = aPLUSb.parent;
					aPLUSb.parent.getChilds().add(smallest,aPLUSb);
					aPLUSb.parent.getChilds().add(smallest,mn);
					aPLUSb.toggleNegative();
				}
			}
		//	RelativeContainer dc = new RelativeContainer();
		//	dc.drawelement(second, (int)im.tr.font);
			Ghost = new image(second, new point(im.bel.x, 80), null);
		}

		return Ghost;
	}
	public LikeTermsGhost(image img){
		im = img;
		downwalk walk = new downwalk(img.tr, this);
	}

	public void performAction(term tr) {
		if(aPLUSb == null){
			if(tr.operator !=null && (tr.operator instanceof plus || tr.operator instanceof minus)){

				term ans = null;
				boolean stop = false;
				for(int i = 0; i<tr.getChilds().size()-1; i++){
					for(int j = i+1; j<tr.getChilds().size(); j++){
						
						if(!stop){
						ans = (tr.getChilds().get(i)).AddLikeTerms(tr.getChilds().get(j));
						}
						if(ans != null && !stop){
							
							aPLUSb = ans;
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
