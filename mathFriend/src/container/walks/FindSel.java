package container.walks;

import representTerms.Image;
import tree.Term;
import tree.compound.CompoundTerm;
import tree.downwalk;
import tree.downwalk.TreeFunction;
//import android.util.Log;
import display.point;

public class FindSel implements TreeFunction{

	static final String TAG = "FindSel";

	public CompoundTerm found = null;
	Image myMain;
	CompoundTerm mySel;
	point myTapped;

	public FindSel(Image main, CompoundTerm sel, point tapped){
		myMain = main;
		mySel = sel;
		myTapped = tapped;
		downwalk dw = new downwalk(main.tr, this);
//		Log.d(TAG, "main: "+myMain.tr.toString()+" sel: "+mySel.toString()+" "+tapped.toString());
	}

//	@Override
	public void performAction(Term tr) {

		boolean sameString = false;
		boolean samePlace = false;

		String selString = mySel.toString();
		String trString = tr.toString(); 
		String trInParens = "("+tr.toString()+")";


		if(found == null){

			if(selString.equals(trString) || selString.equals(trInParens)){
				sameString = true;
			}

		/*	if(tr.ScreenPosition.container.bl.x-2<myTapped.x &&
					tr.ScreenPosition.container.bl.x+tr.ScreenPosition.container.width+2>myTapped.x){
				samePlace = true;
			}*/

			if(samePlace && sameString){
				//found = tr;
			}
		}
		else{}

	}

}
