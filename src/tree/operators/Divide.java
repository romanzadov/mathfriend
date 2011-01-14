package tree.operators;

import representTerms.Image;
import tree.Term;
import display.rectangle;

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
		valuestring = "/";
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
	@Override
	public Term simpleOperation(Term term) {

		if(term.getChilds().size() == 3){
			if(term.getChilds().get(0).isDecimal() && term.getChilds().get(2).isDecimal()){
				
				double value = Term.getNumericValue(term.getChilds().get(0))/Term.getNumericValue(term.getChilds().get(2));
				Image img = new Image(String.valueOf(value),2,2,2);
				return img.tr;
				
			}
			
			
		}
		
		
		return null;
	}
}