package parse;

import java.util.ArrayList;

import tree.simple.simpleterm;

public class preorganized {

	public ArrayList<simpleterm> simplify(ArrayList<simpleterm> asimp){
	
		for(int i = 0; i<asimp.size();i++){
		simpleterm st = asimp.get(i);
		if(st instanceof tree.simple.Number){
		st.valuestring = ""+(((tree.simple.Number) st).value);
		}
		if(st instanceof tree.simple.Constant){
			st.valuestring =""+(((tree.simple.Constant) st).value);
			}
		if(st instanceof tree.operators.parens){
			st.valuestring = ""+(((tree.operators.parens) st).value);
			}
		if(st instanceof tree.simple.variable){
			st.valuestring = ""+(((tree.simple.variable) st).value);
			}
		if(st instanceof tree.operators.Operator){
			st.valuestring = ""+(((tree.operators.Operator) st).thisvalue);
			}
		
		
		
		
		
		}
		
		
		
		return asimp;
		
	}
	
}
