package container.walks;

import java.util.ArrayList;

//import android.util.Log;

import representTerms.stringrect;
import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;

public class AssignScreenPositions  implements TreeFunction{

	ArrayList<stringrect> screenPositions;
	static final String TAG = "AssignScreenPositions";
	
	
	public AssignScreenPositions(Term tr, ArrayList<stringrect> myScreenPositions){
		screenPositions = myScreenPositions;
		downwalk walk = new downwalk(tr, this);
	}

	
	int placeSaver = 0;
	public void performAction(Term tr) {
		try {

			
			tr.ScreenPosition = (stringrect)screenPositions.get(placeSaver).clone();
			placeSaver++;
			
		} catch (CloneNotSupportedException e) {}
		
	}
	

}
