package ghosts;

import tree.Term;
import tree.operators.Minus;
import tree.operators.Plus;
import tree.simple.Number;
//import android.util.Log;

public class SanitizePlus {

	static final String TAG = "SanitizePlus";
	
	public SanitizePlus(Term tr){

		boolean haszero = false;
		int pos = -1;
		for(int i = 0; i<tr.getChilds().size(); i++){
			if(tr.getChilds().get(i) instanceof Number){
				if(((Number)tr.getChilds().get(i)).value == 0){
					haszero = true;
					pos = i;
					break;
				}
			}
			
		}
		if(haszero){
			if((tr.operator instanceof Plus)||(tr.operator instanceof Minus)){
				if(tr.getChilds().size()>1){
					if(pos > 0){
						tr.getChilds().remove(pos-1);
						tr.getChilds().remove(pos-1);
					}
					if(pos == 0){
						tr.getChilds().remove(0);
						if(tr.getChilds().get(0) instanceof Plus){
							tr.getChilds().remove(0);
						}
						else if(tr.getChilds().get(0) instanceof Minus){
							tr.getChilds().remove(0);
							Term next = tr.getChilds().get(0);
//							Log.d(TAG, "next: "+next);
							tr.getChilds().set(0,  next.toggleNegative());
//							Log.d(TAG, "new next: "+next);
						}
					}
					
					
				}
			}
		}
		
		
	
	}
}
