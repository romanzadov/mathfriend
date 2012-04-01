package ghosts;

import representTerms.Image;
import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
//import android.util.Log;

public class SanitizeGhost  implements TreeFunction{

	static final String TAG = "SanitizeGhost";

	public SanitizeGhost(Image Ghost){
//		Log.d(TAG, "old ghost tr: "+Ghost.tr);

		if(Ghost.tr != null){
			downwalk walk = new downwalk(Ghost.tr, this);
		}
//		Log.d(TAG, "ghost tr: "+Ghost.tr);
		try {
			int kids = Ghost.tr.getChildren().size();
			for(int i = 0; i<kids; i++){
				if(!Ghost.tr.getChildren().get(i).issimple && Ghost.tr.getChildren().get(i).getChildren().size()==1){
					Term mid = Ghost.tr.getChildren().get(i).getChildren().get(0);
					mid.setParent(Ghost.tr);
					Ghost.tr.getChildren().set(i, mid);
				}
			}
			//RelativeContainer dn = new RelativeContainer();
			//dn.drawelement(Ghost.tr, (int)Ghost.tr.font);
			Ghost = new Image(Ghost.tr, Ghost.bel, null);

		} catch (Exception e) {}



	}

	public void performAction(Term tr){
		SanitizePlus sp = new SanitizePlus(tr);
	}


}
