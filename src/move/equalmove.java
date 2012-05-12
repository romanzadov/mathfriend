package move;

import move.identify.selectterm;
import representTerms.Image;
import tree.compound.CompoundTerm;
import tree.functions.Equals;

public class equalmove {
	
	public CompoundTerm eqmove(Image im, CompoundTerm selected, int x, int y){
		/*boolean move = false;
		
		CompoundTerm tr = im.tr;
		
		if(!(tr.getFunction() instanceof Equals)){
			System.out.println("error: equalmove run on non equal term");
		}
		else{
			int selectedplace = 0;
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i)==selected){selectedplace = i;}
			}
			selectterm st = new selectterm();
			CompoundTerm over = st.whichterm(im.tr, x, y, im.bel, 0);
			int overplace = 0;
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i)==over){overplace = i; move = true;}
			}
			
			if(move){
				*//*if(!(over instanceof Function)
						&&!(selected instanceof Function)){
				tr = switchedterm(tr, selectedplace, overplace);
				}*//*
			}
		}
		return tr;*/
	    return null;
    }
	
	public CompoundTerm switchedterm(CompoundTerm tr, int selectedplace, int overplace){
		/*CompoundTerm placeholder = tr.getChildren().get(overplace);
		tr.getChildren().set(overplace, tr.getChildren().get(selectedplace));
		tr.getChildren().set(selectedplace, placeholder);
		
		return tr;*/
        return null;
	}

}
