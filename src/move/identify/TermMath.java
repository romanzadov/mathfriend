package move.identify;

import java.util.ArrayList;

import tree.CompoundTerm;

public class TermMath {



	public static ArrayList<Integer> findTreePositionOfSelected(CompoundTerm tr, CompoundTerm sel){
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
	
	public static CompoundTerm findTermUsingKey(CompoundTerm second, ArrayList<Integer> key){
		CompoundTerm middle = second;
		
		for(int i = 0; i<key.size(); i++){
		
			int j = key.size()-i-1;
		
		//	middle = middle.getChildren().get(key.get(j));
			
		}
	
		
		if(middle.getParent().hasParentheses()){
			int midint = middle.getParent().getChildren().indexOf(middle);
		//	middle = middle.getParent().getChildren().get(midint+1);
		}
		
		return middle;
	}

}
