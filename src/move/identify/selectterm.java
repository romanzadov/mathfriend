package move.identify;

import tree.term;
import tree.operators.operator;
//import android.util.Log;
import display.point;
import display.rectangle;

public class selectterm {
	//when a mouse is clicked, this class will find out which
	//term was selected
	//if it isn't over any term, then it will return an empty term

	static final String TAG = "selectterm";
	
	public term whichterm(term tr, int x, int y, point termbl, int error){
		term back = new term();
		
		boolean found = false;

		try {
			if(!tr.issimple ){
				for (int i = 0; i< tr.getChilds().size(); i++){
					if(!(tr.getChilds().get(i) instanceof operator)){
						boolean in = true;
						rectangle ours = tr.getChilds().get(i).ScreenPosition.container;
//						Log.d(TAG, ours.toString()+" click: ("+x+","+y+")");
						point tl = ours.topleft(ours.bl, ours.height);
						//go through the four corners of a rectangle
						if(tl.x-error>x){in = false;}
						if(ours.bl.y+error<(y)){in = false;}
						if(tl.x+ours.width+error<x){in = false;}
						if(tl.y-error>y){in = false;}
						//if still true, stop
						if(in){found = true; back = tr.getChilds().get(i); break;}
						if(!in){back = null;}
					}

				}
			}
			else{
				boolean in = true;
				rectangle ours = tr.container;
				point tl = ours.topleft(ours.bl, ours.height);
				//go through the four corners of a rectangle
				if(tl.x>x){in = false;}
				if(ours.bl.y<(y)){in = false;}
				if(tl.x+ours.width<x){in = false;}
				if(tl.y>y){in = false;}
				//if still true, stop
				if(in){found = true; back = tr;}
				if(!in){back = null;}
			
				
			}

		} catch (Exception e) {
			
		}


		if(!found){back = null;}


		return back;
	}

	//simpler version of select term. This one only cares if you're in the same x range and ignores y
	public term aboveterm(term tr, int x, term exclude, point termbl){
		term back = new term();
		boolean found = false;

		x-=termbl.x;

		try {
			if(!tr.issimple){
				for (int i = 0; i< tr.getChilds().size(); i++){
					if(!(tr.getChilds().get(i)==exclude)){
						boolean in = true;
						rectangle ours = tr.getChilds().get(i).container;
						point tl = ours.topleft(ours.bl, ours.height);
						//go through the four corners of a rectangle
						if(tl.x>x){in = false;}
						if(tl.x+ours.width<x){in = false;}
						//if still true, stop
						if(in){found = true; back = tr.getChilds().get(i); break;}
						if(!in){back = null;}
					}

				}
			}
			else{
				boolean in = true;
				rectangle ours = tr.container;
				point tl = ours.topleft(ours.bl, ours.height);
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


		return back;
		
	}
}
