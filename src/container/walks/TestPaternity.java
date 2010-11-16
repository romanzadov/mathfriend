package container.walks;

import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;

public class TestPaternity implements TreeFunction{


	public TestPaternity(term tr){
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(term tr) {

		for(int i = 0; i<tr.getChilds().size(); i++){
			if(tr.getChilds().get(i).parent != tr){
				tr.getChilds().get(i).parent = tr;
				System.out.println("wrong parent");
			}
			if(tr.parent != null){
				int place = tr.parent.getChilds().indexOf(tr);

				if(place == -1){
					System.out.println("wrong child");
				}
			}
			if(tr.operator == null){
				System.out.println("no operator");
			}
		}

	}

}
