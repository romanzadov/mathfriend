package container.walks;

import java.util.ArrayList;

//import android.util.Log;

import representTerms.stringrect;
import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;

public class AssignScreenPositions  implements TreeFunction{

	ArrayList<stringrect> screenPositions;
	static final String TAG = "AssignScreenPositions";
	
	
	public AssignScreenPositions(term tr, ArrayList<stringrect> myScreenPositions){
		screenPositions = myScreenPositions;
		downwalk walk = new downwalk(tr, this);
	}

	public void performAction(term tr) {
		try {
//			Log.d(TAG, tr.toString()+ screenPositions.size());
			if(screenPositions.size()>0){
			tr.ScreenPosition = (stringrect)screenPositions.get(0).clone();
			screenPositions.remove(0);
		//	Log.d(TAG, tr.toString()+" pos: "+tr.ScreenPosition.container.toString());
			}
		} catch (CloneNotSupportedException e) {}
		
	}
	

}
