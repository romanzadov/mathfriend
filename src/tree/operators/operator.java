package tree.operators;


import representTerms.Image;
import tree.Term;
import tree.notsimple.Equation;
import tree.simple.SimpleTerm;
import display.rectangle;


public abstract class Operator extends SimpleTerm {



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
			return getValueString();
		}
	}

    public abstract Term simpleOperation(Term term);

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
		if(tr.getChildren().size() == 0){
			a.bl.x = 0;
			a.bl.y = 0;
			a.height = 0;
			a.width = 0;
		}
		if(tr.getChildren().size()>0){
			
			//check that all are rectangled
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i).getContainer() == null){
					System.out.println("error: equal called when not all terms are rectangled");
				}
			}
			a.bl.x = tr.getChildren().get(0).getContainer().bl.x;
			a.bl.y = tr.getChildren().get(0).getContainer().bl.y;
			a.width = tr.getChildren().get(0).getContainer().width;
			a.height = tr.getChildren().get(0).getContainer().height;
			
			

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
