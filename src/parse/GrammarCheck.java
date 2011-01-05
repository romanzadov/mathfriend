package parse;

import java.util.ArrayList;


import tree.operators.Operator;


public class GrammarCheck {

	
	public boolean CheckChars(ArrayList<Character> formula){
		boolean good = true;
		ArrayList<Character> bad = new ArrayList<Character>();
		
		int lefts = 0;
		int rights = 0;
		
		
		for( int i = 0; i<formula.size(); i++){
			if(formula.get(i).equals('(')){
				lefts++;
			}
			else if (formula.get(i).equals(')')){
				rights++;
			}
			for(int j = 0; j<Operator.NOTFUNCTIONS.length; j++){
				if(formula.get(i).equals(Operator.NOTFUNCTIONS[j])){
					bad.add(Operator.NOTFUNCTIONS[j]);
				}
			}
			
		}
		
		if(lefts!= rights){
			good = false;
		//	MessageWindow mw = new MessageWindow("You mismatched your parentheses");
		}
		if(bad.size()>0){
			good = false;
			String bd = new String();
			for(int i = 0; i<bad.size(); i++){
				bd+=bad.get(i);
			}
		//	MessageWindow mw = new MessageWindow("I can't recognize  "+bd);
		}
		
		
		return good;
	}
	
}
