package move.operators;

import tree.Term;
import tree.operators.divide;
import tree.operators.Minus;
import tree.operators.Operator;
import tree.operators.Plus;
import tree.operators.times;

public class TimesTerm {
	
	public void Times(Term tr, Term sel){
	
		if(tr.operator instanceof times || tr.operator instanceof divide){
			regularTimes(tr, sel);
		}
		
		else if(tr.operator instanceof Plus || tr.operator instanceof Minus){
			for(int i = 0; i< tr.getChilds().size(); i++){
				if(!(tr.getChilds().get(i) instanceof Operator)){
					
					Term resel = null;
					try {
						resel = (Term)sel.clone();
					} catch (CloneNotSupportedException e) {}
					resel.parent = sel.parent;
					regularTimes(tr.getChilds().get(i), resel);
				}
			}
		}
		else{
			regularTimes(tr, sel);
		}

		
	}
	
	public void regularTimes(Term tr, Term sel){
		times tm = new times();
		if(tr.operator instanceof times || tr.operator instanceof divide){
			sel.parent = tr;
			tm.parent = tr;
			tr.getChilds().add(0,tm);
			tr.getChilds().add(0,sel);
		}
		else{
			Term mid = new Term();
			mid.operator = new times();
			mid.parent = tr.parent;
			
			int trplace = tr.parent.getChilds().indexOf(tr);
			
			mid.getChilds().add(sel);
			mid.getChilds().add(tm);
			mid.getChilds().add(tr);
			
			tr.parent = mid;
			tm.parent = mid;
			sel.parent = mid;
			
			mid.parent.getChilds().set(trplace, mid);
		}
		
	}

}
