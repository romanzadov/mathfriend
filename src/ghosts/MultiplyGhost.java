package ghosts;

import java.util.ArrayList;

import container.RelativeContainer;
import display.point;

import move.identify.FindSel;
import move.identify.ReturnSel;
import representTerms.image;
import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;
import tree.operators.times;

public class MultiplyGhost implements TreeFunction{

	term a = null;
	term b = null;
	term aTIMESb = null;
	image im;

	public image Like(){
		
		image Ghost =null;
		term second = null;
		if(aTIMESb != null){
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
			Ghost = new image(second, new point(im.bel.x, 80), null);
		}
		
		
		return Ghost;
	}
	
	public MultiplyGhost(image img){
		im = img;
		downwalk walk = new downwalk(img.tr, this);
	}


	public void performAction(term tr) {
		if(aTIMESb == null){
			if(tr.operator !=null && (tr.operator instanceof times)){

				term ans = null;
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
