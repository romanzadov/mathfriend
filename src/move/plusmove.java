package move;

import move.identify.selectterm;
import tree.Term;
import tree.operators.Equality;
import tree.operators.Operator;
import tree.operators.Plus;
import display.point;

public class plusmove {

	public Term plmomve(Term main, Term selected, int x, int y){
		System.out.println("plus move");
		selectterm st = new selectterm();		
		Term endterm = st.whichterm(selected.parent, x, y, new point(0,0), 0);
		
		if(endterm == null){
			
			if(selected.parent.parent.operator instanceof Equality){
				Term higherterm = st.whichterm(selected.parent.parent, x, y, new point(0,0), 0);
				endterm = st.whichterm(higherterm, x, y, new point(0,0), 0);
			
			}
		}
		if(!(selected instanceof Operator) && 
				!(endterm instanceof Operator))
		   	{
			System.out.println("try to move");
			main = move(main, selected, endterm);}
		
		
		
		return main;
	}
	
	public Term move(Term main, Term selected, Term endterm){
		
		int splace = selected.parent.getChildren().indexOf(selected);
		int eplace = endterm.parent.getChildren().indexOf(endterm);
		
		//if they're on the same side of the equals
		if(selected.parent==endterm.parent){
			//first, the case that both are positive
			System.out.println("same side ");
			if(!(selected.isNegative()) &&!endterm.isNegative()){
				System.out.println("both positive");
				if(splace==0||eplace==0){
					System.out.println("start or end = 0");
					if(splace==0){
						System.out.println("start = 0");
						if(selected.parent.getChildren().get(1) instanceof Plus){
							Plus pl = new Plus();
							Term hold = selected.parent.getChildren().get(splace);
							selected.parent.getChildren().set(splace,
									selected.parent.getChildren().get(eplace));
							selected.parent.getChildren().set(splace-1,
									selected.parent.getChildren().get(eplace-1));
							selected.parent.getChildren().set(eplace,pl);
							selected.parent.getChildren().set(eplace-1,hold);
						}
						
					}
					
				}
				else{
					Term hold1 = selected.parent.getChildren().get(splace-1);
					Term hold2 = selected.parent.getChildren().get(splace);
					selected.parent.getChildren().set(splace,
							selected.parent.getChildren().get(eplace));
					selected.parent.getChildren().set(splace-1,
							selected.parent.getChildren().get(eplace-1));
					selected.parent.getChildren().set(eplace,hold2);
					selected.parent.getChildren().set(eplace-1,hold1);
					
				}
			}
			
		}
		//or if they're on different sides of =
		else{
			
		}
		
		
		return main;
	}

}
