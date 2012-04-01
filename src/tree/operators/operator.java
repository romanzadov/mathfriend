package tree.operators;


import representTerms.Image;
import tree.Term;
import tree.notsimple.Equation;
import tree.simple.simpleterm;
import display.rectangle;


public abstract class Operator extends simpleterm{



	public int inputs;
	public boolean invertable;
	public boolean commutative;
	public boolean distributive;
	public boolean associative;
	public float identity;
	public double orderofoperation;
	public boolean lmult;
	public boolean rmult;

	final static public String[] KNOWNFUNCTIONS = {"sin","cos",
			"+","-","*","/","^","=","<",">",	
			"tan","csc","cot","log","ln"}; 
	final static public Character[] NOTFUNCTIONS = {'!', '@', '#', '$', '%', ',','~', '|'};
	public String thisvalue;
	public int charpos;
	
	@Override
	public String toString(){
		if(this instanceof Negative){
			return "*";
		}
		else{
			return valuestring;
		}
	}


	public Operator setconstants(Operator a){
		a.thisvalue = thisvalue;
		a.charpos = charpos;
		return a;
	}

	public boolean equals(Object a)
	{
		boolean ans = false;
		if(a.equals(thisvalue)){ans=true;}
		return ans;
	}

	public boolean isoperator(){
		boolean a = true;
		return a;
	}

	public rectangle giverect(Term tr){
		rectangle a = new rectangle();
		if(tr.getChilds().size() == 0){
			a.bl.x = 0;
			a.bl.y = 0;
			a.height = 0;
			a.width = 0;
		}
		if(tr.getChilds().size()>0){
			
			//check that all are rectangled
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(i).container == null){
					System.out.println("error: equal called when not all terms are rectangled");
				}
			}
			a.bl.x = tr.getChilds().get(0).container.bl.x;
			a.bl.y = tr.getChilds().get(0).container.bl.y;
			a.width = tr.getChilds().get(0).container.width;
			a.height = tr.getChilds().get(0).container.height;
			
			

		}
		return a;
	}

	public abstract Image inTermMoves(Image im, Term sel, int IntermIndex);
	
	public abstract Image overEqualsMoves(Image im, Term sel, int IntermIndex, double xsel);

	public Equation ToBothSides(Equation eq, Operator op, Term sel) {
		System.out.println("this method should not run, but delegate to specific operator.");
		return eq;
	}
	
}
