package container.walks;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;

public class killparens  implements TreeFunction{


	public killparens(Term tr){
		
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		/*if(tr instanceof Parens){
			tr.getParent().setHasParentheses(true);
			int n = tr.getParent().getChildren().size();
			for(int i = 0; i<n ;i++){
				if(tr.getParent().getChildren().get(i) == tr){
					tr.getParent().getChildren().remove(i);
					break;
				}
			}
			
		}*/
		
	}
	
	
}
