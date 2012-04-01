package container.walks;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
import tree.operators.Parens;

public class killparens  implements TreeFunction{


	public killparens(Term tr){
		
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		if(tr instanceof Parens){
			tr.parent.hasParentheses = true;
			int n = tr.parent.getChildren().size();
			for(int i = 0; i<n ;i++){
				if(tr.parent.getChildren().get(i) == tr){
					tr.parent.getChildren().remove(i);
					break;
				}
			}
			
		}
		
	}
	
	
}
