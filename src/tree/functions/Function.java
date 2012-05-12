package tree.functions;

import move.operators.TimesTerm;
import parse.PreSimpleTerm;
import representTerms.Image;
import tree.compound.*;

import java.util.*;


public abstract class Function {



	public int inputs;
	public boolean invertible;
	public boolean commutative;
	public boolean distributive;
	public boolean associative;
	public float identity;

	final static public String[] KNOWN_FUNCTIONS = {"+","-","*","/","^","=", "sin", "cos", "tan", "log", "ln"};
	final static public Character[] NOTFUNCTIONS = {'!', '@', '#', '$', '%', ',','~', '|'};

    final static public List<Class<? extends Function>> ORDER_OF_OPERATIONS = new ArrayList<Class<? extends Function>>();
    static {
        ORDER_OF_OPERATIONS.add(Equals.class);
        ORDER_OF_OPERATIONS.add(Plus.class);
        ORDER_OF_OPERATIONS.add(Times.class);
        ORDER_OF_OPERATIONS.add(Exponent.class);
        ORDER_OF_OPERATIONS.add(AdvancedFunction.class);
    }

    final static private Map<Class<? extends Function>, Class<? extends CompoundTerm>> FUNCTION_TO_TERM = new HashMap<Class<? extends Function>, Class<? extends CompoundTerm>>();
    static {
        FUNCTION_TO_TERM.put(Plus.class, AdditiveTerm.class);
        FUNCTION_TO_TERM.put(Times.class, MultiplicativeTerm.class);
        FUNCTION_TO_TERM.put(Equals.class, Equation.class);
        FUNCTION_TO_TERM.put(Exponent.class, ExponentTerm.class);
    }

	public String thisvalue;

    public abstract CompoundTerm simpleOperation(CompoundTerm term);

	public boolean equals(Object a)
	{
		boolean ans = false;
		if(a.equals(thisvalue)){ans=true;}
		return ans;
	}

	public abstract Image inTermMoves(Image im, CompoundTerm sel, int IntermIndex);
	
	public abstract Image overEqualsMoves(Image im, CompoundTerm sel, int IntermIndex, double xsel);

	public Equation toBothSides(Equation eq, Function function, CompoundTerm sel) {
		System.out.println("this method should not run, but delegate to specific operator.");
		return eq;
	}

    public static int getOrderOfOperation(PreSimpleTerm preSimpleTerm) {
        return ORDER_OF_OPERATIONS.indexOf(preSimpleTerm.getFunctionType().getFunction());
    }

    public static int getOrderOfOperation(Class<? extends Function> function) {
        return ORDER_OF_OPERATIONS.indexOf(function);
    }

    public static Class<? extends CompoundTerm> getCompoundTermClass(Class<? extends Function> function) {
        return FUNCTION_TO_TERM.get(function);
    }
}