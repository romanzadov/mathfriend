package move.operators;

import tree.term;
import tree.operators.divide;
import tree.operators.minus;
import tree.operators.operator;
import tree.operators.plus;
import tree.operators.times;

public class TimesTerm {
	
	public void Times(term tr, term sel){
	
		if(tr.operator instanceof times || tr.operator instanceof divide){
			regularTimes(tr, sel);
		}
		
		else if(tr.operator instanceof plus || tr.operator instanceof minus){
			for(int i = 0; i< tr.getChilds().size(); i++){
				if(!(tr.getChilds().get(i) instanceof operator)){
					
					term resel = null;
					try {
						resel = (term)sel.clone();
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
	
	public void regularTimes(term tr, term sel){
		times tm = new times();
		if(tr.operator instanceof times || tr.operator instanceof divide){
			sel.parent = tr;
			tm.parent = tr;
			tr.getChilds().add(0,tm);
			tr.getChilds().add(0,sel);
		}
		else{
			term mid = new term();
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
