package tree.operators;

import display.rectangle;
import tree.*;

public class Divide extends Operator{

	public String todraw = "/";
	
	public Divide(){
		inputs=2;
	    invertable=true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=1;
		orderofoperation=4;
		lmult = false;
		rmult = false;
		valueString = "/";
	}

    @Override
    public Term simpleOperation(Term term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public rectangle giverect(Term tr){
		rectangle a = new rectangle();
		Times ti = new Times();
		a = ti.giverect(tr);
		return a;
	}
	
	
	public double function(double a, double b)
	{
		double c = a/b;
		return c;
	}
	@Override
	public representTerms.Image inTermMoves(representTerms.Image im, Term sel,
			int IntermIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public representTerms.Image overEqualsMoves(representTerms.Image im,
			Term sel, int IntermIndex, double xsel) {
		// TODO Auto-generated method stub
		return null;
	}
}
