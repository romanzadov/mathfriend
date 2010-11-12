package parse;

import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;
import tree.notsimple.Fraction;
import tree.notsimple.NegativeTerm;
import tree.operators.negative;
//import android.util.Log;

public class CastToNonSimple implements TreeFunction{

	public static final String TAG = "CastToNonSimple";
	int n = 0;

	public CastToNonSimple(term tr){
		for(int i = 0; i<2; i++){
			n = i;
			downwalk walk = new downwalk(tr, this);
		}
	}

	public void performAction(term tr) {
		//go through subclasses of "notsimple" and see  we can cast our term as one of them.
		if(n == 0){
			Fraction f = new Fraction();
			if(f.isNegative(tr)){
				int place = tr.parent.getChilds().indexOf(tr);
				f = new Fraction(tr);

				f.parent = tr.parent;
				tr.parent.getChilds().set(place, f);
			}}
		else if(n == 1){
			NegativeTerm n = new NegativeTerm();
			if(n.isNegative(tr)){
				int place = tr.parent.getChilds().indexOf(tr);
				n = new NegativeTerm(tr);
				
				n.parent = tr.parent;
				tr.parent.getChilds().set(place, n);
		
			}
		}

	}

}
