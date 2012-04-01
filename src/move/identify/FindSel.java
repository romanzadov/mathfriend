package move.identify;

import java.util.ArrayList;

import tree.Term;

public class FindSel {



	public ArrayList<Integer> FindSelected(Term tr, Term sel){
		ArrayList<Integer> key = new ArrayList<Integer>();

		Term middle = sel;
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
