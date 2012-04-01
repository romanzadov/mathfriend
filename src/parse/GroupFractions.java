package parse;

import java.util.ArrayList;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
import tree.operators.Divide;
import tree.operators.Times;

public class GroupFractions  implements TreeFunction{

	ArrayList<Integer> dividespots;
	
	public GroupFractions(Term tr){
		
		PreGroupFractions pf = new PreGroupFractions(tr);
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(Term tr) {
		
		//this should run anytime there's more than one fraction
		//in a term. We'll seperate them into seperate terms.
		

		if(tr !=null && tr.getChildren().size()>3){
			dividespots = new ArrayList<Integer>();

			
			for(int i = 0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i) instanceof Divide){
					dividespots.add(i);
				}
			}

			for(int j = 0; j<dividespots.size(); j++){
				
				int i = dividespots.get(j);
					tr.operator = new Times();
				
					Term mid = new Term();
					mid.operator = new Divide();
					mid.parent = tr;
					mid.getChildren().add(tr.getChildren().get(i-1));
					mid.getChildren().add(tr.getChildren().get(i));
					mid.getChildren().add(tr.getChildren().get(i+1));
					
					tr.getChildren().get(i+1).parent = mid;
					tr.getChildren().get(i).parent = mid;
					tr.getChildren().get(i-1).parent = mid;
					
					tr.getChildren().remove(i-1);
					tr.getChildren().remove(i-1);
					//tr.childs.remove(i-1);
					
					tr.getChildren().set(i-1, mid);
					
					for(int k = 0; k<dividespots.size(); k++){
						dividespots.set(k, dividespots.get(k)-2);
					}
					
			}
		}

	}


}
