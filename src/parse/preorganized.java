package parse;

import java.util.ArrayList;

import tree.operators.Parens;
import tree.simple.SimpleTerm;

public class preorganized {

	public ArrayList<SimpleTerm> simplify(ArrayList<SimpleTerm> asimp){
	
		for(int i = 0; i<asimp.size();i++){
		SimpleTerm st = asimp.get(i);
		if(st instanceof tree.simple.Number){
		st.valueString = ""+(((tree.simple.Number) st).value);
		}
		if(st instanceof tree.simple.Constant){
			st.valueString =""+(((tree.simple.Constant) st).value);
			}
		if(st instanceof Parens){
			st.valueString = ""+(((Parens) st).value);
			}
		if(st instanceof tree.simple.variable){
			st.valueString = ""+(((tree.simple.variable) st).value);
			}
		if(st instanceof tree.operators.Operator){
			st.valueString = ""+(((tree.operators.Operator) st).thisvalue);
			}
		
		
		
		
		
		}
		
		
		
		return asimp;
		
	}
	
}
