package move.identify;

import tree.compound.CompoundTerm;
//import android.util.Log;
import display.Point;

public class selectterm {
	//when a mouse is clicked, this class will find out which
	//term was selected
	//if it isn't over any term, then it will return an empty term

	static final String TAG = "selectterm";
	
	public CompoundTerm whichterm(CompoundTerm tr, float x, float y, Point termbl, int error){
	/*	CompoundTerm back = new CompoundTerm();
		
	//	System.out.println(" "+tr+"|"+x+"|"+y+"|"+termbl+"|");
		
		boolean found = false;

		try {
			if(!tr.isSimple() ){
				for (int i = 0; i< tr.getChildren().size(); i++){
					*//*if(!(tr.getChildren().get(i) instanceof Function)){
						boolean in = true;
						rectangle ours = tr.getChildren().get(i).ScreenPosition.container;
//						Log.d(TAG, ours.toString()+" click: ("+x+","+y+")");
						point tl = ours.topleft();
						//go through the four corners of a rectangle
						if(tl.x-error>x){in = false;}
						if(ours.bl.y+error>(y)){in = false;}
						if(tl.x+ours.width+error<x){in = false;}
						if(tl.y-error<y){in = false;}
						//if still true, stop
						if(in){found = true; back = tr.getChildren().get(i); break;}
						if(!in){back = null;}
					}*//*

				}
			}
			else{
				boolean in = true;
				rectangle ours = tr.getContainer();
				point tl = ours.topleft();
				//go through the four corners of a rectangle
				if(tl.x>x){in = false;}
				if(ours.bl.y<(y)){in = false;}
				if(tl.x+ours.width<x){in = false;}
				if(tl.y>y){in = false;}
				//if still true, stop
				if(in){found = true; back = (CompoundTerm) tr.clone();}
				if(!in){back = null;}
			
				
			}

		} catch (Exception e) {
			
		}


		if(!found){back = null;}

	
		
		return back;*/
	    return null;
    }

	//simpler version of select term. This one only cares if you're in the same x range and ignores y
	public CompoundTerm aboveterm(CompoundTerm tr, int x, CompoundTerm exclude, Point termbl){
		/*CompoundTerm back = new CompoundTerm();
		boolean found = false;

		x-=termbl.x;

		try {
			if(!tr.isSimple()){
				for (int i = 0; i< tr.getChildren().size(); i++){
					if(!(tr.getChildren().get(i)==exclude)){
						boolean in = true;
						rectangle ours = tr.getChildren().get(i).getContainer();
						point tl = ours.topleft();
						//go through the four corners of a rectangle
						if(tl.x>x){in = false;}
						if(tl.x+ours.width<x){in = false;}
						//if still true, stop
						if(in){found = true; back = tr.getChildren().get(i); break;}
						if(!in){back = null;}
					}

				}
			}
			else{
				boolean in = true;
				rectangle ours = tr.getContainer();
				point tl = ours.topleft();
				//go through the four corners of a rectangle
				if(tl.x>x){in = false;}
				if(tl.x+ours.width<x){in = false;}
				//if still true, stop
				if(in){found = true; back = tr;}
				if(!in){back = null;}
			
				
			}

		} catch (Exception e) {
			
		}


		if(!found){back = null;}


		return back;*/
        return null;
		
	}
}
