package parse;

import java.util.ArrayList;

import tree.operators.Parens;
import tree.simple.SimpleTerm;

public class PreOrganized {

	public ArrayList<SimpleTerm> simplify(ArrayList<SimpleTerm> asimp){
	
		for(int i = 0; i<asimp.size();i++){
		SimpleTerm st = asimp.get(i);
		if(st instanceof tree.simple.Number){
		st.setValueString(""+(((tree.simple.Number) st).value));
		}
		if(st instanceof tree.simple.Constant){
			st.setValueString(""+(((tree.simple.Constant) st).value));
			}
		if(st instanceof Parens){
			st.setValueString(""+(((Parens) st).value));
			}
		if(st instanceof tree.simple.variable){
			st.setValueString(""+(((tree.simple.variable) st).value));
			}
		if(st instanceof tree.operators.Operator){
			st.setValueString(""+(((tree.operators.Operator) st).thisvalue));
			}
		}

		return asimp;
		
	}
	
}
