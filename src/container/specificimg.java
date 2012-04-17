package container;

import tree.Term;
import tree.functions.Function;
import tree.simple.SimpleTerm;
//import android.util.Log;
import display.rectangle;

public class specificimg {
	
	static final String TAG = "specificimg";
	
	public rectangle specificimage(Term ter){
		
		
		rectangle rect = new rectangle();
		//for each term's children
		for(int i =0; i<ter.getChildren().size();i++){
			//if simple, make rectangle
			if(ter.getChildren().get(i) instanceof SimpleTerm){
				SimpleTerm st = (SimpleTerm)ter.getChildren().get(i);
				ter.getChildren().get(i).setContainer(st.giverect(ter.getChildren().get(i)));
			}
			//if not simple, and not rectangled, dig in
			else if(!ter.getChildren().get(i).issimple){
				Term t2 = ter.getChildren().get(i);
				ter.getChildren().get(i).setContainer(specificimage(t2));
			}
			else{}
		}

		//some low level terms aren't simple, but have no contents.
		
		//once all terms are rectangled, call
		//the operation to make superrectangle
		
		Function op = ter.getOperator();
		if(op!=null){
		rect = op.giverect(ter);}
		else {
			
			SimpleTerm pop = (SimpleTerm)ter;
			rect = pop.giverect(ter);
		}
		
		ter.setContainer(rect);
		//add parentheses as needed
		if(ter.isHasParentheses()){
			addParentheses(ter);
//			Log.d(TAG, "tr: "+ter.toString()+ "  true");
		}
		
		//return rectangle
		return rect;
	}
		
	public void addParentheses(Term tr){
		//these will be the width and height of the parentheses
		float height = tr.getContainer().height;
		float width = height/2;
		
		tr.getContainer().width += width+width;
		
		for(int i = 0; i<tr.getChildren().size(); i++){
			//move all the contents over by the width of the parens
			tr.getChildren().get(i).getContainer().bl.x+=width;
		}
	}

}
