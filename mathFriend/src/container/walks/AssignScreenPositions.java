package container.walks;

import java.util.ArrayList;

//import android.util.Log;

import representTerms.StringRectangle;
import tree.Term;
import tree.compound.CompoundTerm;
import tree.downwalk;
import tree.downwalk.TreeFunction;

public class AssignScreenPositions  implements TreeFunction{

	ArrayList<StringRectangle> screenPositions;
	static final String TAG = "AssignScreenPositions";
	
	
	public AssignScreenPositions(CompoundTerm tr, ArrayList<StringRectangle> myScreenPositions){
		screenPositions = myScreenPositions;
		downwalk walk = new downwalk(tr, this);
	}

	
	int placeSaver = 0;
	public void performAction(Term tr) {
		/*try {

			
		//	tr.ScreenPosition = (StringRectangle)screenPositions.get(placeSaver).clone();
			placeSaver++;
			
		} catch (CloneNotSupportedException e) {}*/
		
	}


}
