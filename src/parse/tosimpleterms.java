package parse;
import java.util.ArrayList;

import tree.operators.Operator;
import tree.simple.SimpleTerm;
import tree.simple.variable;

public class tosimpleterms {

	public ArrayList<SimpleTerm> simples;
	public int placeholder = 0;
	public Operator op = null;
	 boolean donenum=false;

	public ArrayList<SimpleTerm> simplify(ArrayList<Character> formula){

		for(int i =0; i<formula.size(); i++){
			char ch = formula.get(i);
			if(Character.isLetter(ch)==true){//differentiate between variables and functions
				if(formula.size()-i>4){
					char[] three = {ch,formula.get(i+1),formula.get(i+2)};
					String tr = new String(three);
					for(int j = 0; j<Operator.KNOWNFUNCTIONS.length;j++){
						if(Operator.KNOWNFUNCTIONS[i]==tr){
							//fill in what to do with things like sin, cos
							System.out.println("you put in an unhandled function");
						}
					}
				}
				else{	
					variable var = new variable();
					String st = new String();
					st = st+ch;
					var.setvalue(st);
					simples.add(var);
				}

			}
			
			else if(Character.isDigit(ch)==true || ch=='.'){
				int j = i+1;
				while(donenum == false){
					if(Character.isDigit(formula.get(j))==true
						||	formula.get(j)=='.'
						||  formula.get(j)==','){
					
						
					}
					
				}
				
				
			}
		}




		return simples;

	}

}
