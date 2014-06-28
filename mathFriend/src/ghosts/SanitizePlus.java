package ghosts;

import tree.compound.CompoundTerm;
import tree.simple.Number;
//import android.util.Log;

public class SanitizePlus {

	static final String TAG = "SanitizePlus";
	
	public SanitizePlus(CompoundTerm tr){

		boolean haszero = false;
		int pos = -1;
		for(int i = 0; i<tr.getChildren().size(); i++){
			if(tr.getChildren().get(i) instanceof Number){
				if(((Number)tr.getChildren().get(i)).value == 0){
					haszero = true;
					pos = i;
					break;
				}
			}
			
		}
		if(haszero){
			/*if((tr.getFunction() instanceof Plus)||(tr.getFunction() instanceof Minus)){
				if(tr.getChildren().size()>1){
					if(pos > 0){
						tr.getChildren().remove(pos-1);
						tr.getChildren().remove(pos-1);
					}
					if(pos == 0){
						tr.getChildren().remove(0);
						if(tr.getChildren().get(0) instanceof Plus){
							tr.getChildren().remove(0);
						}
						else if(tr.getChildren().get(0) instanceof Minus){
							tr.getChildren().remove(0);
							Term next = tr.getChildren().get(0);
//							Log.d(TAG, "next: "+next);
							tr.getChildren().set(0,  next.toggleNegative());
//							Log.d(TAG, "new next: "+next);
						}
					}
					
					
				}
			}*/
		}
		
		
	
	}
}
