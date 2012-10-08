package ghosts;

import representTerms.Image;
import tree.Term;
import tree.compound.CompoundTerm;
import tree.downwalk;
import tree.downwalk.TreeFunction;

public class LikeTermsGhost implements TreeFunction{

	CompoundTerm a = null;
	CompoundTerm b = null;
	CompoundTerm aPLUSb = null;
	Image im;

	public Image Like(){
/*		Image Ghost =null;
		CompoundTerm second = null;
		if(aPLUSb != null){
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
			
			as.getParent().getChildren().remove(biggest-1);
			as.getParent().getChildren().remove(biggest-1);
			if(smallest == 0){
                as.getParent().getChildren().remove(smallest);}
			else{
				as.getParent().getChildren().remove(smallest-1);
				as.getParent().getChildren().remove(smallest-1);
			}
			
			
			if(smallest>0){smallest --;}
			
			aPLUSb.setParent(as.getParent());
			if(smallest == 0){
				as.getParent().getChildren().add(0,aPLUSb);
			}
			else{
				if(!aPLUSb.isNegative()){
					Plus pl = new Plus();
				//	pl.setParent(aPLUSb.getParent());
					aPLUSb.getParent().getChildren().add(smallest,aPLUSb);
				//	aPLUSb.getParent().getChildren().add(smallest,pl);
				}
				else{
*//*					Minus mn = new Minus();
					mn.setParent(aPLUSb.getParent());
					aPLUSb.getParent().getChildren().add(smallest,aPLUSb);
					aPLUSb.getParent().getChildren().add(smallest,mn);
					aPLUSb.toggleNegative();*//*
				}
			}
		//	RelativeContainer dc = new RelativeContainer();
		//	dc.drawelement(second, (int)im.tr.font);
			Ghost = new Image(second, new point(im.bel.x, 80), null);
		}

		return Ghost;*/
        return null;
	}
	public LikeTermsGhost(Image img){
		im = img;
		downwalk walk = new downwalk(img.tr, this);
	}

	public void performAction(Term tr) {
		if(aPLUSb == null){
/*			if(tr.getFunction() !=null && (tr.getFunction() instanceof Plus || tr.getFunction() instanceof Minus)){

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
			}*/
		}
	}



}
