package container;

import tree.Term;
import tree.operators.Operator;
import tree.simple.simpleterm;
//import android.util.Log;
import display.rectangle;

public class specificimg {
	
	static final String TAG = "specificimg";
	
	public rectangle specificimage(Term ter){
		
		
		rectangle rect = new rectangle();
		//for each term's children
		for(int i =0; i<ter.getChilds().size();i++){
			//if simple, make rectangle
			if(ter.getChilds().get(i) instanceof simpleterm){
				simpleterm st = (simpleterm)ter.getChilds().get(i);
				ter.getChilds().get(i).container = st.giverect(ter.getChilds().get(i));
			}
			//if not simple, and not rectangled, dig in
			else if(!ter.getChilds().get(i).issimple){
				Term t2 = ter.getChilds().get(i);
				ter.getChilds().get(i).container = specificimage(t2);
			}
			else{System.out.println("not all terms are rectangles!");}
		}

		//some low level terms aren't simple, but have no contents.
		
		//once all terms are rectangled, call
		//the operation to make superrectangle
		
		Operator op = ter.operator;
		if(op!=null){
		rect = op.giverect(ter);}
		else {
			
			simpleterm pop = (simpleterm)ter;
			rect = pop.giverect(ter);
		}
		
		ter.container = rect;
		//add parentheses as needed
		if(ter.hasparen){
			addParentheses(ter);
//			Log.d(TAG, "tr: "+ter.toString()+ "  true");
		}
		
		//return rectangle
		return rect;
	}
		
	public void addParentheses(Term tr){
		//these will be the width and height of the parentheses
		float height = tr.container.height;
		float width = height/2;
		
		tr.container.width += width+width;
		
		for(int i = 0; i<tr.getChilds().size(); i++){
			//move all the contents over by the width of the parens
			tr.getChilds().get(i).container.bl.x+=width;
		}
	}

}
