package move.identify;

import java.util.ArrayList;

import tree.Term;

public class FindSel {



	public ArrayList<Integer> FindSelected(Term tr, Term sel){
		ArrayList<Integer> key = new ArrayList<Integer>();

		Term middle = sel;
		while(middle != tr){
			for(int i = 0; i<middle.parent.getChildren().size(); i++){
				if(middle.parent.getChildren().get(i)==middle){
					key.add(i);

				}
			}
			middle = middle.parent;
		}



		return key;
	}

}
