package move.identify;

import java.util.ArrayList;

import tree.CompoundTerm;

public class FindSel {



	public ArrayList<Integer> FindSelected(CompoundTerm tr, CompoundTerm sel){
		ArrayList<Integer> key = new ArrayList<Integer>();

		CompoundTerm middle = sel;
		while(middle != tr){
			for(int i = 0; i< middle.getParent().getChildren().size(); i++){
				if(middle.getParent().getChildren().get(i)==middle){
					key.add(i);

				}
			}
			middle = middle.getParent();
		}



		return key;
	}

}
