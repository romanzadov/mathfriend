package move;

import move.identify.selectterm;
import representTerms.Images;
import tree.Term;
import tree.functions.Equality;
import tree.functions.Function;

public class equalmove {
	
	public Term eqmove(Images im, Term selected, int x, int y){
		boolean move = false;
		
		Term tr = im.tr;
		
		if(!(tr.getFunction() instanceof Equality)){
			System.out.println("error: equalmove run on non equal term");
		}
		else{
			int selectedplace = 0;
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i)==selected){selectedplace = i;}
			}
			selectterm st = new selectterm();
			Term over = st.whichterm(im.tr, x, y, im.bel, 0);
			int overplace = 0;
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i)==over){overplace = i; move = true;}
			}
			
			if(move){
				if(!(over instanceof Function)
						&&!(selected instanceof Function)){
				tr = switchedterm(tr, selectedplace, overplace);
				}
			}
		}
		return tr;
	}
	
	public Term switchedterm(Term tr, int selectedplace, int overplace){
		Term placeholder = tr.getChildren().get(overplace);
		tr.getChildren().set(overplace, tr.getChildren().get(selectedplace));
		tr.getChildren().set(selectedplace, placeholder);
		
		return tr;
	}

}
