package container.walks;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;

public class TestPaternity implements TreeFunction{


	public TestPaternity(Term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {

		for(int i = 0; i<tr.getChilds().size(); i++){
			if(tr.getChilds().get(i).parent != tr){
				tr.getChilds().get(i).parent = tr;
			
			}
			if(tr.parent != null){
				int place = tr.parent.getChilds().indexOf(tr);

				if(place == -1){
				}
			}
			if(tr.operator == null){
			}
		}

	}

}
