package ghosts;

import container.walks.FindSel;
import representTerms.Image;
import tree.Term;
import tree.functions.*;
//import android.util.Log;
import display.point;
import display.rectangle;

public class GhostImage {
	//when you drag around a term, this class returns what the new 
	//image should look like once you let go
	Term SwitchMe;
	static final String TAG = "GhostImage";

	public Image newImage(Image im, Term sel, point current, point tapped){


		Image Ghost = im;
		int selIndex = 0;

		sel = findSel(im, sel, tapped);

	//	Log.d(TAG, "im: "+im.tr+" sel: "+sel+" curr: "+current+" tapped: "+tapped);
		
		if(sel.getParent() !=null){
			selIndex = sel.getParent().getChildren().indexOf(sel);
			double xsel = current.x;
			int IntermIndex = Integer.MAX_VALUE;
			//	Log.d(TAG, "kids: "+sel.parent.getChilds().size()+" parent: "+sel.parent.toString());
			for (int i = 0; i< sel.getParent().getChildren().size(); i++){
				//		Log.d(TAG, "i: "+i+" over: "+sel.parent.getChilds().get(i).toString());
				rectangle a = sel.getParent().getChildren().get(i).ScreenPosition.container;
				//		Log.d(TAG, xsel+" tr: "+sel.parent.getChilds().get(i).toString()+"  bl.x: "+a.bl.x+" w: "+a.width);

				if(xsel>=(a.bl.x) && xsel<=(a.bl.x+a.width)){
					IntermIndex = i;
					break;
				}

			}
			//Log.d(TAG, "interm index: "+IntermIndex+"");

			// move terms around within term's parent
			if(IntermIndex!=Integer.MAX_VALUE  && selIndex != IntermIndex){

				SwitchMe = sel.getParent().getChildren().get(IntermIndex);
				/*if(!(SwitchMe instanceof Function)
						&& !(SwitchMe instanceof Parens)){
					Function op = sel.getParent().getFunction();
					Ghost = op.inTermMoves(im, sel, IntermIndex);
					//					Log.d(TAG, "index: "+IntermIndex+" sel: "+sel.toString()+" Ghost: "+Ghost);
				}

				else{Ghost = null;}*/
			}


			// if the parent of the parent is an equals, move terms there
			else{Ghost = CheckEquals(im, sel, xsel, IntermIndex);}
			if(Ghost !=null && Ghost.tr!=null){
				SanitizeGhost sg = new SanitizeGhost(Ghost);

			}
		}
		return Ghost;

	}



	public Image CheckEquals(Image im, Term sel, double xsel, int IntermIndex ){
		Image Ghost = new Image();
		int selindex = 0;
		if((sel.getParent() !=null)&&
				(sel.getParent().getParent() !=null)&&
				(sel.getParent().getParent().getFunction() !=null)&&
				(sel.getParent().getParent().getFunction() instanceof Equality)){

			selindex = sel.getParent().getParent().getChildren().indexOf(sel.getParent());
			
			for (int i = 0; i< sel.getParent().getParent().getChildren().size(); i++){
				rectangle a = sel.getParent().getParent().getChildren().get(i).ScreenPosition.container;
				if(xsel>(a.bl.x) && xsel<(a.bl.x+a.width)){
					IntermIndex = i;
					break;
				}

			}
//			Log.d(TAG, "sel: "+selindex+" interm: "+IntermIndex);
			if(IntermIndex != Integer.MAX_VALUE && selindex!=IntermIndex){
				SwitchMe = sel.getParent().getParent().getChildren().get(IntermIndex);
				Function op = sel.getParent().getFunction();
				Ghost = op.overEqualsMoves(im, sel, IntermIndex, xsel);

			}

		}


		/*if(sel.getParent() !=null &&
				(sel.getParent().getFunction() instanceof Times || sel.getParent().getFunction() instanceof Divide)){
			if(sel.getParent().getParent() != null && (sel.getParent().getParent().getFunction() instanceof Plus
					|| sel.getParent().getParent().getFunction() instanceof Minus)){
				if(sel.getParent().getParent().getParent() != null && sel.getParent().getParent().getParent().getFunction() instanceof Equality){

					for (int i = 0; i< sel.getParent().getParent().getParent().getChildren().size(); i++){
						rectangle a = sel.getParent().getParent().getParent().getChildren().get(i).getContainer();
						if(xsel>(a.bl.x) && xsel<(a.bl.x+a.width)){
							IntermIndex = i;
							break;
						}

					}
					if(IntermIndex != Integer.MAX_VALUE){
						SwitchMe = sel.getParent().getParent().getParent().getChildren().get(IntermIndex);
						Function op = sel.getParent().getFunction();
						Ghost = op.overEqualsMoves(im, sel, IntermIndex, xsel);

					}

				}


			}
			if(Ghost !=null){
				SanitizeGhost sg = new SanitizeGhost(Ghost);
			}

		}*/
	
//		Log.d(TAG, "index: "+IntermIndex+" sel: "+sel+" Ghost: "+Ghost);

		return Ghost;
	}

	public Term findSel(Image main, Term sel, point tapped){

		FindSel fs = new FindSel(main, sel, tapped);
		if(fs.found==null){
			fs.found = new Times();
		}
		return fs.found;
	}


}
