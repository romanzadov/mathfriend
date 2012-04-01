package tree.operators;

import container.AllignFracBar;

import move.operators.TimesMove;

import parse.composefractions;
import representTerms.Image;

import display.rectangle;
import tree.*;

public class Times extends Operator{

	public boolean visible = false;
	
	
	public Times(){
		inputs=2;
		invertable=true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=1;
		orderofoperation=4;
		lmult = false;
		rmult = false;
		valueString = "*";
		
	}

    @Override
    public Term simpleOperation(Term term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public rectangle giverect(Term tr){
		

		rectangle rect = new rectangle();
		
		//rectangle any simpleterm
		if(	tr.getChildren().size()==1	){
			if( tr instanceof Times){

				rectangle a = new rectangle();
				a.width = 1;
				a.height = 1;
				tr.container = a;
				tr.toDraw = "*";
				rect = a;
			}
			if( tr instanceof Divide){
				rectangle a = new rectangle();
				a.width = 1;
				a.height = 1;
				tr.container = a;
				rect = a;

			}
		}
		//find the bottoms of the fractions
		
		//this should only be called when all terms are rectangled
		
		else if(tr.getChildren().size()>1){
			//strip parens from previous time. They'll be added later.
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr instanceof Parens){
					tr.getChildren().remove(0);
					tr.getChildren().remove(tr.getChildren().size()-1);
				}
			}
			//make times visible as needed
			for(int i = 0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i) instanceof Times && ((Times)tr.getChildren().get(i)).visible){
				
					Term kid = tr.getChildren().get(i);
					kid.container.height = (float) .3;
					kid.container.width = (float) .3;
					kid.toDraw = ".";
				}
			}
			
			tr.container = makefractions(tr);
			rect = tr.container;
		}
		
		return rect;
	}
	
		
	public rectangle makefractions(Term tr){
	
		//set which terms are bottoms
		for(int i = 0; i<tr.getChildren().size(); i++){
			if(tr.getChildren().size()>0){
			if(tr.getChildren().get(i) instanceof Divide)
			{tr.getChildren().get(i+1).isbottom = true;}
			}
		}
		//find tallest bottom term
		float tallestbottom = 0;
		for(int i = 0; i<tr.getChildren().size(); i++){
			if(tr.getChildren().get(i).isbottom){
				if(tr.getChildren().get(i).container.height>tallestbottom)
				{tallestbottom = tr.getChildren().get(i).container.height;}
			}
		}
		//now that we have the tallest bottom, set bar height
		float barheight = tallestbottom;
		//make a list of bottoms
		
		composefractions cf = new composefractions(tr);
		int[][] places = cf.fracmatrix(tr);
		rectangle thiscont = cf.tofracs(tr, places, barheight);
		tr.container = thiscont;
		boolean thisfrac = false;
		if(tr.getChildren().size() == 3 && tr.operator instanceof Divide){
			thisfrac = true;
		}
		if(!thisfrac){
		AllignFracBar al = new AllignFracBar(tr);
		}
		thiscont = tr.container;
		return thiscont;
		
		
		
	}
	TimesMove tm = new TimesMove();
	@Override
	public Image inTermMoves(Image im, Term sel,
			int IntermIndex) {
		
		Image Ghost = tm.inTermMoves(im, sel, IntermIndex);
		return Ghost;
	}
	@Override
	public representTerms.Image overEqualsMoves(representTerms.Image im,
			Term sel, int IntermIndex, double xsel) {
		
		Image Ghost = tm.overEqualsMoves(im, sel, IntermIndex, xsel);	
		return Ghost;
	}
	
	
}
