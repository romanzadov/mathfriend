package ghosts;

import tree.Term;
import tree.operators.Divide;
import tree.operators.Times;

public class SanitizeNegative {

	public SanitizeNegative(Term tr){

		if(tr.operator instanceof Times || tr.operator instanceof Divide){
			for(int i = 0; i<tr.getChildren().size(); i++){

				




			}
		}
	}

}
