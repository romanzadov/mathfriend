package parse;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
import tree.notsimple.Fraction;
import tree.notsimple.NegativeTerm;
//import android.util.Log;

public class CastToNonSimple implements TreeFunction{

	public static final String TAG = "CastToNonSimple";
	int n = 0;

	public CastToNonSimple(Term tr){
		for(int i = 0; i<3; i++){
			n = i;
			downwalk walk = new downwalk(tr, this);
		}
	}

	public void performAction(Term tr) {
		//go through subclasses of "notsimple" and see  we can cast our term as one of them.
		if(n == 0){
			Fraction f = new Fraction();
			if(f.canConstruct(tr)){
				int place = tr.getParent().getChildren().indexOf(tr);
				f = new Fraction(tr.getChildren().get(0), tr.getChildren().get(2));

				f.setParent(tr.getParent());
				tr.getParent().getChildren().set(place, f);
			}}
		else if(n == 1){
			NegativeTerm n = new NegativeTerm();
			if(n.canConstruct(tr)){
				int place = tr.getParent().getChildren().indexOf(tr);
				n = new NegativeTerm(tr);
				
				n.setParent(tr.getParent());
				tr.getParent().getChildren().set(place, n);
		
			}
		}
		
/*
		else if(n == 2){
			Equations equation = new Equations();
			if(equation.canConstruct(tr)){
				int place = tr.parent.getChilds().indexOf(tr);
				equation = new Equations(tr);

				equation.parent = tr.parent;
				tr.parent.getChilds().set(place, equation);
			}
			
		}
*/

/*
		//make times visible if needed. 
		 Times.makeVisible(tr);
		*/
	}

}
