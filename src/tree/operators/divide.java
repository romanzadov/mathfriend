package tree.operators;

import display.rectangle;
import tree.*;

public class divide extends operator{

	public String todraw = "/";
	
	public divide(){
		inputs=2;
	    invertable=true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=1;
		orderofoperation=4;
		lmult = false;
		rmult = false;
		valuestring = "/";
	}
	public rectangle giverect(term tr){
		rectangle a = new rectangle();
		times ti = new times();
		a = ti.giverect(tr);
		return a;
	}
	
	
	public double function(double a, double b)
	{
		double c = a/b;
		return c;
	}
	@Override
	public representTerms.Image inTermMoves(representTerms.Image im, term sel,
			int IntermIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public representTerms.Image overEqualsMoves(representTerms.Image im,
			term sel, int IntermIndex, double xsel) {
		// TODO Auto-generated method stub
		return null;
	}
}