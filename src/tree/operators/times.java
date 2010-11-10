package tree.operators;
import java.util.ArrayList;

import container.AllignFracBar;

import move.operators.timesmove;

import parse.composefractions;
import representTerms.image;

import display.rectangle;
import tree.*;

public class times extends operator{

	public boolean visible = false;
	
	
	public times(){
		inputs=2;
		invertable=true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=1;
		orderofoperation=4;
		lmult = false;
		rmult = false;
		valuestring = "*";
		
	}
	public rectangle giverect(term tr){
		

		rectangle rect = new rectangle();
		
		//rectangle any simpleterm
		if(	tr.getChilds().size()==1	){
			if( tr instanceof times){

				rectangle a = new rectangle();
				a.width = 1;
				a.height = 1;
				tr.container = a;
				tr.todraw = "*";
				rect = a;
			}
			if( tr instanceof divide){
				rectangle a = new rectangle();
				a.width = 1;
				a.height = 1;
				tr.container = a;
				rect = a;

			}
		}
		//find the bottoms of the fractions
		
		//this should only be called when all terms are rectangled
		
		else if(tr.getChilds().size()>1){
			//strip parens from previous time. They'll be added later.
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr instanceof parens){
					tr.getChilds().remove(0);
					tr.getChilds().remove(tr.getChilds().size()-1);
				}
			}
			//make times visible as needed
			for(int i = 0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(i) instanceof times && ((times)tr.getChilds().get(i)).visible){
				
					term kid = tr.getChilds().get(i);
					kid.container.height = (float) .3;
					kid.container.width = (float) .3;
					kid.todraw = ".";
				}
			}
			
			tr.container = makefractions(tr);
			rect = tr.container;
		}
		
		return rect;
	}
	
		
	public rectangle makefractions(term tr){
	
		//set which terms are bottoms
		for(int i = 0; i<tr.getChilds().size(); i++){
			if(tr.getChilds().size()>0){
			if(tr.getChilds().get(i) instanceof divide)
			{tr.getChilds().get(i+1).isbottom = true;}
			}
		}
		//find tallest bottom term
		float tallestbottom = 0;
		for(int i = 0; i<tr.getChilds().size(); i++){
			if(tr.getChilds().get(i).isbottom){
				if(tr.getChilds().get(i).container.height>tallestbottom)
				{tallestbottom = tr.getChilds().get(i).container.height;}
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
		if(tr.getChilds().size() == 3 && tr.operator instanceof divide ){
			thisfrac = true;
		}
		if(!thisfrac){
		AllignFracBar al = new AllignFracBar(tr);
		}
		thiscont = tr.container;
		return thiscont;
		
		
		
	}
	timesmove tm = new timesmove();
	@Override
	public image inTermMoves(image im, term sel,
			int IntermIndex) {
		
		image Ghost = tm.inTermMoves(im, sel, IntermIndex);
		return Ghost;
	}
	@Override
	public representTerms.image overEqualsMoves(representTerms.image im,
			term sel, int IntermIndex, double xsel) {
		
		image Ghost = tm.overEqualsMoves(im, sel, IntermIndex, xsel);	
		return Ghost;
	}
	
	
}
