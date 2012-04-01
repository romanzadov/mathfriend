package tree.notsimple;

import tree.Term;
import tree.simple.Number;

public class MultiplyFractions {

	
	public Term times(Term a, Term b){
		
		Term ans = null;
		
		
		
		if( a.isSimpleFraction() && b.isInteger()){
			ans = fractimesnumber(a, b);
		}
		else if( a.isInteger() && b.isSimpleFraction()){
			ans = fractimesnumber(b, a);
		}
		else if( a.isSimpleFraction() && b.isSimpleFraction()){
			ans = fractimesfrac(a, b);
		}
		
	
		
		return ans;
	}
	
	public Term fractimesnumber(Term A, Term B){

		Term sel = A;
		Term tr = B;
		
			double a = sel.getNumericValue(sel.getChildren().get(0));
			double b = sel.getNumericValue(tr);
			double c = a*b;
			Term n = new Term();
			if(c>=0){
				n = new Number(c);
			}
			else{
				n = new Number(-c);
				n = n.toggleNegative();
			}
			Term second = null;
			try {
				 second = (Term)sel.clone();
			} catch (CloneNotSupportedException e) {}
			
			n.setParent(second);
			second.getChildren().set(0, n);
			
			return second;
		
	}
	
	public Term fractimesfrac(Term A, Term B){
		
		Term sel = A;
		Term tr = B;
		
		double a = sel.getNumericValue(sel.getChildren().get(0));
		double b = tr.getNumericValue(tr.getChildren().get(0));
		double toptimes = a*b;
		Term top = new Term();
		if(toptimes>=0){
			top = new Number(toptimes);
		}
		else{
			top = new Number(-toptimes);
			top = top.toggleNegative();
		}
		
		double c = sel.getNumericValue(sel.getChildren().get(2));
		double d = tr.getNumericValue(tr.getChildren().get(2));
		double bottomtimes = c*d;
		
		Term bottom = new Term();
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
