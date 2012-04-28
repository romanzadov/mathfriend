package java_specific;

import java.util.ArrayList;

import droid.tests.TestStrings;

import representTerms.Images;
import representTerms.LogicEngine;
import tree.Term;

public class RunAsJava {

	public static void main(String[] args) {

		String st = "1.5x-3=7";

		RunAsJava raj = new RunAsJava();
		    raj.testNumbers();
			raj.testAddtion();
			raj.testMultiplication();
			raj.startFrame(st);
		    raj.testEquations();
	}

	private void testEquations(){
		
		ArrayList<String> tests = TestStrings.getStrings();
		int wrongSoFar = 0;
		
		for(int i = 0; i<tests.size(); i++){
			Images equation = new Images(tests.get(i),2,2,2);
			Term first = equation.tr.getChildren().get(0);
			Term second = equation.tr.getChildren().get(2);

			Term result = first;
			
			if(equality(result, second)){}
			else{
				try{
				System.out.println("BAD: "+tests.get(i)+" | "+" "+result+" = "+second);
				}
				catch(Exception e){System.out.println("Null point returned: "+tests.get(i));}
				wrongSoFar++;
			}
		}

		String stats = "Percent Correct: "+(tests.size()-wrongSoFar)+"/"+tests.size()+" | "+(float)(tests.size()-wrongSoFar)/tests.size();
		
		System.out.println(stats);
	}

	private boolean equality(Term result, Term second){
		boolean equal = false;
		if(result == null){return false;}
		
		for(int i = 0; i<result.getChildren().size(); i++){
			if(result.getChildren().get(i) == null){return false;}
		}
			
			Images img2 = new Images(result.toString(), 2,2,2);
			
			if(result.toString().equals(second.toString()) ||
					("("+result.toString()+")").equals(second.toString()) ||
					img2.tr.toString().equals(second.toString())){
				equal = true;
			}
		
		
		
		return equal;
	}
	
	public void testMultiplication(){

		String st = "(2/3)(4/5)(3/9)";
		Images img = new Images(st, 2,2,2);
		System.out.println(img.tr);
		for(int i = 0; i<4; i++){
			img.tr = img.tr.getResultOfOperation();
			System.out.println(img.tr);
		}

	}

	public void testAddtion(){

		String st = "-4+6+5";
		Images img = new Images(st, 2,2,2);
		System.out.println(img.tr);
		for(int i = 0; i<4; i++){
			img.tr = img.tr.getResultOfOperation();
			System.out.println(img.tr);
		}
	}

	public void testNumbers(){
		String[] tests = { "5", "0", "-3", "-4.1", "5/3", "-3/4", "pi", "e"};

		for(int i = 0; i<tests.length; i++){

			Images img = new Images(tests[i],2,2,2);
			Term tr = img.tr;
			String type = tr.toString()+"|";

			if(tr.isNatural()){type+=" isnatural |";}
			if(tr.isWhole()){type += "isWhole |";}
			if(tr.isInteger()){type+=" isInteger |";}
			if(tr.isRationalNumber()){type+= " isRational |";}
			if(tr.isRealNumber()){type += " isReal |";}
			if(tr.isNegative()){type+=" isnegative |";}
			if(tr.isSimple()){type+=" issimple |";}

			type += " val: "+ Term.getNumericValue(tr);

			System.out.println(type);
		}
	}

	public void startFrame(String st){

		LogicEngine.init(st);
		LogicEngine LOGIC_ENGINE = LogicEngine.getLogicEngine();
	}
	


	
}
