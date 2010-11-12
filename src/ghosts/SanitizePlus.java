package ghosts;

import tree.term;
import tree.operators.minus;
import tree.operators.plus;
import tree.simple.Number;
//import android.util.Log;

public class SanitizePlus {

	static final String TAG = "SanitizePlus";
	
	public SanitizePlus(term tr){

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
			if((tr.operator instanceof plus)||(tr.operator instanceof minus)){
				if(tr.getChilds().size()>1){
					if(pos > 0){
						tr.getChilds().remove(pos-1);
						tr.getChilds().remove(pos-1);
					}
					if(pos == 0){
						tr.getChilds().remove(0);
						if(tr.getChilds().get(0) instanceof plus){
							tr.getChilds().remove(0);
						}
						else if(tr.getChilds().get(0) instanceof minus){
							tr.getChilds().remove(0);
							term next = tr.getChilds().get(0);
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
