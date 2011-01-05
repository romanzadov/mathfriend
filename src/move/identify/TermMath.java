package move.identify;

import java.util.ArrayList;

import tree.Term;

public class TermMath {



	public static ArrayList<Integer> findTreePositionOfSelected(Term tr, Term sel){
		ArrayList<Integer> key = new ArrayList<Integer>();

		Term middle = sel;
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
	
	public static Term findTermUsingKey(Term second, ArrayList<Integer> key){
		Term middle = second;
		
		for(int i = 0; i<key.size(); i++){
		
			int j = key.size()-i-1;
		
			middle = middle.getChilds().get(key.get(j));
			
		}
	
		
		if(middle.parent.hasparen){
			int midint = middle.parent.getChilds().indexOf(middle);
			middle = middle.parent.getChilds().get(midint+1);
		}
		
		return middle;
	}

}
