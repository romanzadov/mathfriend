package ghosts;

import tree.Term;
import tree.operators.divide;
import tree.operators.times;

public class SanitizeNegative {

	public SanitizeNegative(Term tr){

		if(tr.operator instanceof times || tr.operator instanceof divide){
			for(int i = 0; i<tr.getChilds().size(); i++){

				




			}
		}
	}

}
