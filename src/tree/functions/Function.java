package tree.functions;

import com.sun.org.apache.xpath.internal.operations.Or;
import parse.PreSimpleTerm;
import representTerms.Image;
import tree.Term;
import tree.notsimple.Equation;
import tree.simple.SimpleTerm;
import display.rectangle;

import java.util.*;


public abstract class Function extends SimpleTerm {



	public int inputs;
	public boolean invertable;
	public boolean commutative;
	public boolean distributive;
	public boolean associative;
	public float identity;
	public boolean lmult;
	public boolean rmult;

	final static public String[] KNOWN_FUNCTIONS = {"+","-","*","/","^","=", "sin", "cos", "tan", "log", "ln"};
	final static public Character[] NOTFUNCTIONS = {'!', '@', '#', '$', '%', ',','~', '|'};
    final static public Map<PreSimpleTerm.FunctionType, Class<? extends Function>> PRE_SIMPLE_TERM_TO_FUNCTION = new HashMap<PreSimpleTerm.FunctionType, Class<? extends Function>>();
    {
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.PLUS, Plus.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.MINUS, Plus.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.TIMES, Times.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.DIVIDE, Times.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.NEGATIVE, Times.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.EXPONENT, Exponent.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.EQUALITY, Equality.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.SINE, AdvancedFunction.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.COSINE, AdvancedFunction.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.TANGENT, AdvancedFunction.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.LOG, AdvancedFunction.class);
        PRE_SIMPLE_TERM_TO_FUNCTION.put(PreSimpleTerm.FunctionType.LN, AdvancedFunction.class);
    }
    final static public List<Class<? extends Function>> ORDER_OF_OPERATIONS = new ArrayList<Class<? extends Function>>();
    {
        ORDER_OF_OPERATIONS.add(Equality.class);
        ORDER_OF_OPERATIONS.add(Plus.class);
        ORDER_OF_OPERATIONS.add(Times.class);
        ORDER_OF_OPERATIONS.add(Exponent.class);
        ORDER_OF_OPERATIONS.add(AdvancedFunction.class);
    }
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

	public Function setconstants(Function a){
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

	public Equation ToBothSides(Equation eq, Function op, Term sel) {
		System.out.println("this method should not run, but delegate to specific operator.");
		return eq;
	}

    public static int getOrderOfOperation(PreSimpleTerm preSimpleTerm) {
        return ORDER_OF_OPERATIONS.indexOf(PRE_SIMPLE_TERM_TO_FUNCTION.get(preSimpleTerm));
    }

    public static Class<? extends Function> getFunction(PreSimpleTerm preSimpleTerm) {
        return PRE_SIMPLE_TERM_TO_FUNCTION.get(preSimpleTerm);
    }
	
}
