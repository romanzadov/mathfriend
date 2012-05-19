package move;

import move.identify.selectterm;
import tree.compound.CompoundTerm;
import display.Point;
import tree.functions.Equals;

public class plusmove {

	public CompoundTerm plmomve(CompoundTerm main, CompoundTerm selected, int x, int y){
		System.out.println("plus move");
		selectterm st = new selectterm();		
		CompoundTerm endterm = st.whichterm(selected.getParent(), x, y, new Point(0,0), 0);
		
		if(endterm == null){
			
			if(selected.getParent().getParent().getFunction() instanceof Equals){
				CompoundTerm higherterm = st.whichterm(selected.getParent().getParent(), x, y, new Point(0,0), 0);
				endterm = st.whichterm(higherterm, x, y, new Point(0,0), 0);
			
			}
		}
/*		if(!(selected instanceof Function) &&
				!(endterm instanceof Function))
		   	{
			System.out.println("try to move");
			main = move(main, selected, endterm);}*/
		
		
		
		return main;
	}
	
	public CompoundTerm move(CompoundTerm main, CompoundTerm selected, CompoundTerm endterm){
		
		int splace = selected.getParent().getChildren().indexOf(selected);
		int eplace = endterm.getParent().getChildren().indexOf(endterm);
		
		//if they're on the same side of the equals
		if(selected.getParent() == endterm.getParent()){
			//first, the case that both are positive
			System.out.println("same side ");
			if(!(selected.isNegative()) &&!endterm.isNegative()){
				System.out.println("both positive");
				if(splace==0||eplace==0){
					System.out.println("start or end = 0");
					if(splace==0){
						System.out.println("start = 0");
						/*if(selected.getParent().getChildren().get(1) instanceof Plus){
							Plus pl = new Plus();
							Term hold = selected.getParent().getChildren().get(splace);
							selected.getParent().getChildren().set(splace,
									selected.getParent().getChildren().get(eplace));
							selected.getParent().getChildren().set(splace-1,
									selected.getParent().getChildren().get(eplace-1));
							selected.getParent().getChildren().set(eplace,pl);
							selected.getParent().getChildren().set(eplace-1,hold);
						}*/
						
					}
					
				}
				else{
			/*		CompoundTerm hold1 = selected.getParent().getChildren().get(splace-1);
					CompoundTerm hold2 = selected.getParent().getChildren().get(splace);
					selected.getParent().getChildren().set(splace,
							selected.getParent().getChildren().get(eplace));
					selected.getParent().getChildren().set(splace-1,
							selected.getParent().getChildren().get(eplace-1));
					selected.getParent().getChildren().set(eplace,hold2);
					selected.getParent().getChildren().set(eplace-1,hold1);*/
					
				}
			}
			
		}
		//or if they're on different sides of =
		else{
			
		}
		
		
		return main;
	}

}
