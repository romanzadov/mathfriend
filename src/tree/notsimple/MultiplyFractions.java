package tree.notsimple;

import tree.term;
import tree.operators.divide;
import tree.operators.times;
import tree.simple.Number;

public class MultiplyFractions {

	
	public term times(term a, term b){
		
		term ans = null;
		
		
		
		if( a.Fraction() && b.NaturalNumber()){
			ans = fractimesnumber(a, b);
		}
		else if( a.NaturalNumber() && b.Fraction()){
			ans = fractimesnumber(b, a);
		}
		else if( a.Fraction() && b.Fraction()){
			ans = fractimesfrac(a, b);
		}
		
	
		
		return ans;
	}
	
	public term fractimesnumber(term A, term B){

		term sel = A;
		term tr = B;
		
			double a = sel.truenum(sel.getChilds().get(0));
			double b = sel.truenum(tr);
			double c = a*b;
			term n = new term();
			if(c>=0){
				n = new Number(c);
			}
			else{
				n = new Number(-c);
				n = n.toggleNegative();
			}
			term second = null;
			try {
				 second = (term)sel.clone();
			} catch (CloneNotSupportedException e) {}
			
			n.parent = second;
			second.getChilds().set(0, n);
			
			return second;
		
	}
	
	public term fractimesfrac(term A, term B){
		
		term sel = A;
		term tr = B;
		
		double a = sel.truenum(sel.getChilds().get(0));
		double b = tr.truenum(tr.getChilds().get(0));
		double toptimes = a*b;
		term top = new term();
		if(toptimes>=0){
			top = new Number(toptimes);
		}
		else{
			top = new Number(-toptimes);
			top = top.toggleNegative();
		}
		
		double c = sel.truenum(sel.getChilds().get(2));
		double d = tr.truenum(tr.getChilds().get(2));
		double bottomtimes = c*d;
		
		term bottom = new term();
		if(bottomtimes >=0){
			bottom = new Number(bottomtimes);
		}
		else{
			bottom = new Number(-bottomtimes);
			bottom = bottom.toggleNegative();
		}
		
		Fraction ans = new Fraction(top, bottom);
		
		return ans;
	}
	
}
