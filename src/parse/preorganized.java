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
		if(st instanceof tree.simple.constants){
			st.valuestring =""+(((tree.simple.constants) st).value);
			}
		if(st instanceof tree.operators.parens){
			st.valuestring = ""+(((tree.operators.parens) st).value);
			}
		if(st instanceof tree.simple.variable){
			st.valuestring = ""+(((tree.simple.variable) st).value);
			}
		if(st instanceof tree.operators.operator){
			st.valuestring = ""+(((tree.operators.operator) st).thisvalue);
			}
		
		
		
		
		
		}
		
		
		
		return asimp;
		
	}
	
}
