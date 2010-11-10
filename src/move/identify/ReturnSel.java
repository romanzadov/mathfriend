package move.identify;

import java.util.ArrayList;

import tree.term;

public class ReturnSel {

	
	public term Return(term second, ArrayList<Integer> key){
		term middle = second;
		
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
