package parse;
import java.util.ArrayList;

import tree.*;
import tree.operators.advancedoperator;
import tree.operators.operator;
import tree.operators.parens;
import tree.simple.constants;
import tree.simple.Number;
import tree.simple.simpleterm;
import tree.simple.variable;

public class presimple {

	ArrayList<simpleterm> simp = new ArrayList<simpleterm>();
	public boolean done = false;
	
	public ArrayList<simpleterm> simplify(ArrayList<Character> formula)
	{


		for(int i=0; i<formula.size(); i++){
			done = false;
			
			if(done==false){i = isoperator(i,formula);}
			if(done==false){i = isconstant(i,formula);}
			if(done==false){i = isvariable(i,formula);}
			if(done==false){i = isnumber(i,formula);}
			if(done==false){i = isparen(i,formula);}
			//	isother(i,formula);

		}


		return simp;
	}

	public int isconstant(int i,ArrayList<Character> formula){

		constants c = new constants();
		int out = i;

		for(int j = 0; j<c.CONSTANTS.length;j++){
			String con = c.CONSTANTS[j];
			char[] cons = con.toCharArray();
			int falses = 0;                         

			if(formula.size()-i>=cons.length){

				for(int k = 0; k<cons.length;k++){

					String a = String.valueOf(formula.get(i+k));
					String b = String.valueOf(cons[k]);

					if(a.equals(b)==false){
						falses++;
					}
				}

				if(falses == 0){
					constants thiscon = new constants();
					thiscon.value = thiscon.values[j];
					thiscon.charpos = i;
					simp.add(thiscon);
					out = i+cons.length-1;
					j = c.CONSTANTS.length;
					done = true;
				}

			}


		}
		return out;
	}

	public int isoperator(int i,ArrayList<Character> formula){

		//operator op = new operator();
		int out = i;
		
		for(int j = 0; j<operator.KNOWNFUNCTIONS.length;j++){
			String con = operator.KNOWNFUNCTIONS[j];
			char[] cons = con.toCharArray();
			int falses = 0;                         

			if(formula.size()-i>=cons.length){

				for(int k = 0; k<cons.length;k++){

					String a = String.valueOf(formula.get(i+k));
					String b = String.valueOf(cons[k]);

					if(a.equals(b)==false){
						falses++;
					}
				}

				if(falses == 0){
					advancedoperator thisop = new advancedoperator();
					
					thisop.thisvalue = operator.KNOWNFUNCTIONS[j];
					thisop.charpos = i;
					simp.add(thisop);
					out=i+cons.length-1;
					j = operator.KNOWNFUNCTIONS.length;
					done = true;
				
				}

			}


		}
		return out;
	}

	public int isvariable(int i, ArrayList<Character> formula){
		int out = i;
		if(Character.isLetter(formula.get(i))==true){
			variable var = new variable();
			var.value = String.valueOf(formula.get(i));
			simp.add(var);
			done=true;
			
		}
		return out;
	}

	public int isnumber(int i, ArrayList<Character> formula){
		int ans = i;
		int numlength = 0;
		boolean numberdone = false;
		while(numberdone == false && ans<formula.size()){
			if(Character.isDigit(formula.get(ans))==true
					||Character.valueOf(formula.get(ans))==','
						||Character.valueOf(formula.get(ans))=='.'
			){
				numlength++;	
				ans++;
			}
			else{numberdone = true;}
		}
		if(numlength>0){
		
			String number = new String();
				for(int k = 0;k<numlength;k++){
					number=number+formula.get(i+k);
				}
			double n = Double.parseDouble(number);
			Number numobj = new Number();
			numobj.value = n;
			numobj.charpos = i;
			simp.add(numobj);
			done = true;
			ans = i+numlength-1;
		}

		
		return ans;
	}

	public int isparen(int i, ArrayList<Character> formula){
		int out = i;
			if(Character.valueOf(formula.get(i))=='('){
				parens lp = new parens();
				lp.setvalue('(');
				simp.add(lp);
				done = true;
			}
			else if(Character.valueOf(formula.get(i))==')'){
				parens rp = new parens();
				rp.setvalue(')');
				simp.add(rp);
				done = true;
			}
			else{}
			return out;
	
	}



}
