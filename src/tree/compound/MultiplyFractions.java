package tree.compound;


public class MultiplyFractions {

	
	public CompoundTerm times(CompoundTerm a, CompoundTerm b){
		
		CompoundTerm ans = null;
		
		
		/*
		if( a.isSimpleFraction() && b.isInteger()){
			ans = fractimesnumber(a, b);
		}
		else if( a.isInteger() && b.isSimpleFraction()){
			ans = fractimesnumber(b, a);
		}
		else if( a.isSimpleFraction() && b.isSimpleFraction()){
			//ans = fractimesfrac(a, b);
		}*/
		
	
		
		return ans;
	}
	
	public CompoundTerm fractimesnumber(CompoundTerm A, CompoundTerm B){

		/*CompoundTerm sel = A;
		CompoundTerm tr = B;
		
			double a = sel.getNumericValue(sel.getChildren().get(0));
			double b = sel.getNumericValue(tr);
			double c = a*b;
			CompoundTerm n = new CompoundTerm();
			if(c>=0){
				n = new Number(c);
			}
			else{
				n = new Number(-c);
				n = n.toggleNegative();
			}
			CompoundTerm second = null;
			try {
				 second = (CompoundTerm)sel.clone();
			} catch (CloneNotSupportedException e) {}
			
			n.setParent(second);
			second.getChildren().set(0, n);
			
			return second;
		
	}
	
	public CompoundTerm fractimesfrac(CompoundTerm A, CompoundTerm B){
		
		CompoundTerm sel = A;
		CompoundTerm tr = B;
		
		double a = sel.getNumericValue(sel.getChildren().get(0));
		double b = tr.getNumericValue(tr.getChildren().get(0));
		double toptimes = a*b;
		CompoundTerm top = new CompoundTerm();
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
		
		CompoundTerm bottom = new CompoundTerm();
		if(bottomtimes >=0){
			bottom = new Number(bottomtimes);
		}
		else{
			bottom = new Number(-bottomtimes);
			bottom = bottom.toggleNegative();
		}
		
		Fraction ans = new Fraction(top, bottom);
		
		return ans;*/
        return null;
	}
	
}
