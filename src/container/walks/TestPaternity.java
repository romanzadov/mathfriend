package container.walks;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;

public class TestPaternity implements TreeFunction{


	public TestPaternity(Term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {

		if(tr != null){
			for(int i = 0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i).getParent() != tr){
					tr.getChildren().get(i).setParent(tr);

				}
				if(tr.getParent() != null){
					int place = tr.getParent().getChildren().indexOf(tr);

					if(place == -1){
					}
				}
				if(tr.getOperator() == null){
				}
			}

		}
	}
}
