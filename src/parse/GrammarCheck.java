package parse;

import java.util.ArrayList;

import tree.functions.Function;


public class GrammarCheck {

	
	public static boolean CheckChars(ArrayList<Character> formula){
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
			for(int j = 0; j< Function.NOTFUNCTIONS.length; j++){
				if(formula.get(i).equals(Function.NOTFUNCTIONS[j])){
					bad.add(Function.NOTFUNCTIONS[j]);
				}
			}
			
		}
		
		if(lefts!= rights){
			good = false;
		}
		if(bad.size()>0){
			good = false;
		}
		
		
		return good;
	}
	
}
