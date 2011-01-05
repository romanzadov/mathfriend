package container.walks;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
import tree.operators.parens;

public class killparens  implements TreeFunction{


	public killparens(Term tr){
		
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		if(tr instanceof parens){
			tr.parent.hasparen = true;
			int n = tr.parent.getChilds().size();
			for(int i = 0; i<n ;i++){
				if(tr.parent.getChilds().get(i) == tr){
					tr.parent.getChilds().remove(i);
					break;
				}
			}
			
		}
		
	}
	
	
}
