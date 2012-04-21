package ghosts;

import tree.Term;
import tree.functions.Divide;
import tree.functions.Times;

public class SanitizeNegative {

	public SanitizeNegative(Term tr){

		if(tr.getFunction() instanceof Times || tr.getFunction() instanceof Divide){
			for(int i = 0; i<tr.getChildren().size(); i++){

				




			}
		}
	}

}
