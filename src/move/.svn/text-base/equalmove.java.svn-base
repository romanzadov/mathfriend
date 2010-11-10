package move;

import move.identify.selectterm;
import representTerms.image;
import tree.term;
import tree.operators.equals;
import tree.operators.operator;

public class equalmove {
	
	public term eqmove(image im, term selected, int x, int y){
		boolean move = false;
		
		term tr = im.tr;
		
		if(!(tr.operator instanceof equals)){
			System.out.println("error: equalmove run on non equal term");
		}
		else{
			int selectedplace = 0;
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(i)==selected){selectedplace = i;}
			}
			selectterm st = new selectterm();
			term over = st.whichterm(im.tr, x, y, im.bel, 0);
			int overplace = 0;
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(i)==over){overplace = i; move = true;}
			}
			
			if(move){
				if(!(over instanceof operator)
						&&!(selected instanceof operator)){
				tr = switchedterm(tr, selectedplace, overplace);
				}
			}
		}
		return tr;
	}
	
	public term switchedterm(term tr, int selectedplace, int overplace){
		term placeholder = tr.getChilds().get(overplace);
		tr.getChilds().set(overplace, tr.getChilds().get(selectedplace));
		tr.getChilds().set(selectedplace, placeholder);
		
		return tr;
	}

}
