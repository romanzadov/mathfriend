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
			int kids = Ghost.tr.getChilds().size();
			for(int i = 0; i<kids; i++){
				if(!Ghost.tr.getChilds().get(i).issimple && Ghost.tr.getChilds().get(i).getChilds().size()==1){
					Term mid = Ghost.tr.getChilds().get(i).getChilds().get(0);
					mid.parent = Ghost.tr;
					Ghost.tr.getChilds().set(i, mid);
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
