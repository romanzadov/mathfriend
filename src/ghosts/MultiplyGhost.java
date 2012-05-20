package ghosts;

import representTerms.Image;
import tree.Term;
import tree.compound.CompoundTerm;
import tree.downwalk;
import tree.downwalk.TreeFunction;

public class MultiplyGhost implements TreeFunction{

	CompoundTerm a = null;
	CompoundTerm b = null;
	CompoundTerm aTIMESb = null;
	Image im;

	public Image Like(){
		
		/*Image Ghost =null;
		CompoundTerm second = null;
		if(aTIMESb != null){
			try {
				second = (CompoundTerm) im.tr.clone();
			} catch (CloneNotSupportedException e) {}

			ArrayList<Integer> key = TermMath.findTreePositionOfSelected(im.tr, a);
			CompoundTerm as = TermMath.findTermUsingKey(second, key);
			key = TermMath.findTreePositionOfSelected(im.tr, b);
			CompoundTerm bs = TermMath.findTermUsingKey(second, key);

			int smallest = Integer.MAX_VALUE;

			int ap = as.getParent().getChildren().indexOf(as);
			int bp = bs.getParent().getChildren().indexOf(bs);
			
			smallest = Math.min(ap, bp);
			int biggest = Math.max(ap, bp);
			
			if(as.getParent().getChildren().size()==3){
				int place = as.getParent().getParent().getChildren().indexOf(as.getParent());
				as.getParent().getParent().getChildren().set(place, aTIMESb);
				aTIMESb.setParent(as.getParent().getParent());
			}
			else{
				as.getParent().getChildren().remove(biggest-1);
				as.getParent().getChildren().remove(biggest-1);
				as.getParent().getChildren().set(smallest, aTIMESb);
				aTIMESb.setParent(as.getParent());
			}
			
			RelativeContainer dc = new RelativeContainer();
			dc.drawelement(second);
			Ghost = new Image(second, new point(im.bel.x, 80), null);
		}
		
		
		return Ghost;*/
        return null;
	}
	
	public MultiplyGhost(Image img){
		im = img;
		downwalk walk = new downwalk(img.term, this);
	}


	public void performAction(Term tr) {
		/*if(aTIMESb == null){
			if(tr.getFunction() !=null && (tr.getFunction() instanceof Times)){

				CompoundTerm ans = null;
				boolean stop = false;
				for(int i = 0; i<tr.getChildren().size()-1; i++){
					for(int j = i+1; j<tr.getChildren().size(); j++){
						

						if(!stop){
						ans = (tr.getChildren().get(i)).MultiplyLikeTerms(tr.getChildren().get(j));
						}
						if(ans != null && !stop){
							
							aTIMESb = ans;
							a = tr.getChildren().get(i);
							b = tr.getChildren().get(j);
							stop  = true;
						
						}
						
					}
				}
			}
		}
*/


	}

}
