package move.identify;

import tree.Term;
import tree.operators.Operator;
//import android.util.Log;
import display.point;
import display.rectangle;

public class selectterm {
	//when a mouse is clicked, this class will find out which
	//term was selected
	//if it isn't over any term, then it will return an empty term

	static final String TAG = "selectterm";
	
	public Term whichterm(Term tr, float x, float y, point termbl, int error){
		Term back = new Term();
		
	//	System.out.println(" "+tr+"|"+x+"|"+y+"|"+termbl+"|");
		
		boolean found = false;

		try {
			if(!tr.issimple ){
				for (int i = 0; i< tr.getChildren().size(); i++){
					if(!(tr.getChildren().get(i) instanceof Operator)){
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
					}

				}
			}
			else{
				boolean in = true;
				rectangle ours = tr.container;
				point tl = ours.topleft();
				//go through the four corners of a rectangle
				if(tl.x>x){in = false;}
				if(ours.bl.y<(y)){in = false;}
				if(tl.x+ours.width<x){in = false;}
				if(tl.y>y){in = false;}
				//if still true, stop
				if(in){found = true; back = (Term) tr.clone();}
				if(!in){back = null;}
			
				
			}

		} catch (Exception e) {
			
		}


		if(!found){back = null;}

	
		
		return back;
	}

	//simpler version of select term. This one only cares if you're in the same x range and ignores y
	public Term aboveterm(Term tr, int x, Term exclude, point termbl){
		Term back = new Term();
		boolean found = false;

		x-=termbl.x;

		try {
			if(!tr.issimple){
				for (int i = 0; i< tr.getChildren().size(); i++){
					if(!(tr.getChildren().get(i)==exclude)){
						boolean in = true;
						rectangle ours = tr.getChildren().get(i).container;
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
				rectangle ours = tr.container;
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


		return back;
		
	}
}
