package move;

import move.identify.selectterm;
import representTerms.Image;
import tree.Term;
import tree.operators.equals;
import tree.operators.Operator;

public class equalmove {
	
	public Term eqmove(Image im, Term selected, int x, int y){
		boolean move = false;
		
		Term tr = im.tr;
		
		if(!(tr.operator instanceof equals)){
			System.out.println("error: equalmove run on non equal term");
		}
		else{
			int selectedplace = 0;
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(i)==selected){selectedplace = i;}
			}
			selectterm st = new selectterm();
			Term over = st.whichterm(im.tr, x, y, im.bel, 0);
			int overplace = 0;
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(i)==over){overplace = i; move = true;}
			}
			
			if(move){
				if(!(over instanceof Operator)
						&&!(selected instanceof Operator)){
				tr = switchedterm(tr, selectedplace, overplace);
				}
			}
		}
		return tr;
	}
	
	public Term switchedterm(Term tr, int selectedplace, int overplace){
		Term placeholder = tr.getChilds().get(overplace);
		tr.getChilds().set(overplace, tr.getChilds().get(selectedplace));
		tr.getChilds().set(selectedplace, placeholder);
		
		return tr;
	}

}
