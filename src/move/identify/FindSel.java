package move.identify;

import java.util.ArrayList;

import tree.term;

public class FindSel {



	public ArrayList<Integer> FindSelected(term tr, term sel){
		ArrayList<Integer> key = new ArrayList<Integer>();

		term middle = sel;
		while(middle != tr){
			for(int i = 0; i<middle.parent.getChilds().size(); i++){
				if(middle.parent.getChilds().get(i)==middle){
					key.add(i);

				}
			}
			middle = middle.parent;
		}



		return key;
	}

}
