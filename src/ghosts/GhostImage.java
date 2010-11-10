package ghosts;

import container.walks.FindSel;
import representTerms.image;
import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;
import tree.operators.divide;
import tree.operators.equals;
import tree.operators.minus;
import tree.operators.operator;
import tree.operators.parens;
import tree.operators.plus;
import tree.operators.times;
//import android.util.Log;
import display.point;
import display.rectangle;

public class GhostImage {
	//when you drag around a term, this class returns what the new 
	//image should look like once you let go
	term SwitchMe;
	static final String TAG = "GhostImage";

	public image newImage(image im, term sel, point current, point tapped){


		image Ghost = im;
		int selIndex = 0;

		sel = findSel(im, sel, tapped);

	//	Log.d(TAG, "im: "+im.tr+" sel: "+sel+" curr: "+current+" tapped: "+tapped);
		
		if(sel.parent!=null){
			selIndex = sel.parent.getChilds().indexOf(sel);
			double xsel = current.x;
			int IntermIndex = Integer.MAX_VALUE;
			//	Log.d(TAG, "kids: "+sel.parent.getChilds().size()+" parent: "+sel.parent.toString());
			for (int i = 0; i<sel.parent.getChilds().size(); i++){
				//		Log.d(TAG, "i: "+i+" over: "+sel.parent.getChilds().get(i).toString());
				rectangle a = sel.parent.getChilds().get(i).ScreenPosition.container;
				//		Log.d(TAG, xsel+" tr: "+sel.parent.getChilds().get(i).toString()+"  bl.x: "+a.bl.x+" w: "+a.width);

				if(xsel>=(a.bl.x) && xsel<=(a.bl.x+a.width)){
					IntermIndex = i;
					break;
				}

			}
			//Log.d(TAG, "interm index: "+IntermIndex+"");

			// move terms around within term's parent
			if(IntermIndex!=Integer.MAX_VALUE  && selIndex != IntermIndex){

				SwitchMe = sel.parent.getChilds().get(IntermIndex);
				if(!(SwitchMe instanceof operator)
						&& !(SwitchMe instanceof parens)){
					operator op = sel.parent.operator;
					Ghost = op.inTermMoves(im, sel, IntermIndex);
					//					Log.d(TAG, "index: "+IntermIndex+" sel: "+sel.toString()+" Ghost: "+Ghost);
				}

				else{Ghost = null;}
			}


			// if the parent of the parent is an equals, move terms there
			else{Ghost = CheckEquals(im, sel, xsel, IntermIndex);}
			if(Ghost !=null && Ghost.tr!=null){
				SanitizeGhost sg = new SanitizeGhost(Ghost);

			}
		}
		return Ghost;

	}



	public image CheckEquals(image im, term sel, double xsel, int IntermIndex ){
		image Ghost = new image();
		int selindex = 0;
		if((sel.parent!=null)&&
				(sel.parent.parent!=null)&&
				(sel.parent.parent.operator!=null)&&
				(sel.parent.parent.operator instanceof equals)){

			selindex = sel.parent.parent.getChilds().indexOf(sel.parent);
			
			for (int i = 0; i<sel.parent.parent.getChilds().size(); i++){
				rectangle a = sel.parent.parent.getChilds().get(i).ScreenPosition.container;
				if(xsel>(a.bl.x) && xsel<(a.bl.x+a.width)){
					IntermIndex = i;
					break;
				}

			}
//			Log.d(TAG, "sel: "+selindex+" interm: "+IntermIndex);
			if(IntermIndex != Integer.MAX_VALUE && selindex!=IntermIndex){
				SwitchMe = sel.parent.parent.getChilds().get(IntermIndex);
				operator op = sel.parent.operator;
				Ghost = op.overEqualsMoves(im, sel, IntermIndex, xsel);

			}

		}


		if(sel.parent !=null && 
				(sel.parent.operator instanceof times || sel.parent.operator instanceof divide)){
			if(sel.parent.parent != null && (sel.parent.parent.operator instanceof plus
					|| sel.parent.parent.operator instanceof minus)){
				if(sel.parent.parent.parent != null && sel.parent.parent.parent.operator instanceof equals){

					for (int i = 0; i<sel.parent.parent.parent.getChilds().size(); i++){
						rectangle a = sel.parent.parent.parent.getChilds().get(i).container;
						if(xsel>(a.bl.x) && xsel<(a.bl.x+a.width)){
							IntermIndex = i;
							break;
						}

					}
					if(IntermIndex != Integer.MAX_VALUE){
						SwitchMe = sel.parent.parent.parent.getChilds().get(IntermIndex);
						operator op = sel.parent.operator;
						Ghost = op.overEqualsMoves(im, sel, IntermIndex, xsel);

					}

				}


			}
			if(Ghost !=null){
				SanitizeGhost sg = new SanitizeGhost(Ghost);
			}

		}
	
//		Log.d(TAG, "index: "+IntermIndex+" sel: "+sel+" Ghost: "+Ghost);

		return Ghost;
	}

	public term findSel(image main, term sel, point tapped){

		FindSel fs = new FindSel(main, sel, tapped);
		if(fs.found==null){
			fs.found = new times();
		}
		return fs.found;
	}


}