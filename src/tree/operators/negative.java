package tree.operators;

import display.rectangle;
import tree.*;


public class negative extends operator{
	
	
	public negative(){
		inputs =1;
	    invertable=true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=0;
		orderofoperation=3.5;
		lmult = false;
		rmult = false;
		valuestring = "-";
	}
	
	public rectangle giverect(term tr){
		float xsofar = 0;
		float heightmax = 0;
		rectangle a = new rectangle();
		if(tr.getChilds().size()==0){
			a = justnegative(tr);
		}
	
		else{
			plus pl = new plus();
			a = pl.giverect(tr);
		}
		
		return a;
	}

	public rectangle justnegative(term tr){
		rectangle a = new rectangle();
		tr.todraw = "-";
		a.height = 1;
		a.width = 0.5f;
		tr.container = a;
		return a;
	}
	
	public double function(double a)
	{
		double c = (-1)*a;
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
