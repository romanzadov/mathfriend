package tree.operators;

import display.rectangle;
import tree.*;


public class Negative extends Operator{
	
	
	public Negative(){
		inputs =1;
	    invertable=true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=0;
		orderofoperation=3.5;
		lmult = false;
		rmult = false;
		setValueString("-");
	}

    @Override
    public Term simpleOperation(Term term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public rectangle giverect(Term tr){
		float xsofar = 0;
		float heightmax = 0;
		rectangle a = new rectangle();

			Plus pl = new Plus();
			a = pl.giverect(tr);

		return a;
	}

	
	public double function(double a)
	{
		double c = (-1)*a;
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
