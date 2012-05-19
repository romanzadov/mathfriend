package tree.functions;

import move.operators.TimesMove;

import display.Rectangle;
import representTerms.Image;
import tree.compound.CompoundTerm;

public class Times extends Function {

	public boolean visible = false;
	
	
	public Times(){
		inputs=2;
		invertible =true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=1;
		
	}

    @Override
    public String toString() {
        return "*";
    }


    @Override
    public CompoundTerm simpleOperation(CompoundTerm term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Rectangle giverect(CompoundTerm tr){
		

		Rectangle rect = new Rectangle();
		
		//rectangle any simpleterm
		if(	tr.getChildren().size()==1	){
			/*if( tr instanceof Times){

				rectangle a = new rectangle();
				a.width = 1;
				a.height = 1;
				tr.setContainer(a);

				rect = a;
			}*/
		}
		//find the bottoms of the fractions
		
		//this should only be called when all terms are rectangled
		
		else if(tr.getChildren().size()>1){

			//make times visible as needed
			for(int i = 0; i<tr.getChildren().size(); i++){
				/*if(tr.getChildren().get(i) instanceof Times && ((Times)tr.getChildren().get(i)).visible){
				
					Term kid = tr.getChildren().get(i);
					kid.getContainer().height = (float) .3;
					kid.getContainer().width = (float) .3;
				}*/
			}
			
			tr.setContainer(makefractions(tr));
			rect = tr.getContainer();
		}
		
		return rect;
	}
	
		
	public Rectangle makefractions(CompoundTerm tr){
	
/*		//set which terms are bottoms
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
				if(tr.getChildren().get(i).getContainer().height>tallestbottom)
				{tallestbottom = tr.getChildren().get(i).getContainer().height;}
			}
		}
		//now that we have the tallest bottom, set bar height
		float barheight = tallestbottom;
		//make a list of bottoms
		
		composefractions cf = new composefractions(tr);
		int[][] places = cf.fracmatrix(tr);
		rectangle thiscont = cf.tofracs(tr, places, barheight);
		tr.setContainer(thiscont);
		boolean thisfrac = false;
		if(tr.getChildren().size() == 3 && tr.getFunction() instanceof Divide){
			thisfrac = true;
		}
		if(!thisfrac){
		AllignFracBar al = new AllignFracBar(tr);
		}
		thiscont = tr.getContainer();
		return thiscont;
		
		*/
		return null;
	}
	TimesMove tm = new TimesMove();
	@Override
	public Image inTermMoves(Image im, CompoundTerm sel,
			int IntermIndex) {
		
		Image Ghost = tm.inTermMoves(im, sel, IntermIndex);
		return Ghost;
	}
	@Override
	public Image overEqualsMoves(Image im,
			CompoundTerm sel, int IntermIndex, double xsel) {
		
		Image Ghost = tm.overEqualsMoves(im, sel, IntermIndex, xsel);
		return Ghost;
	}
	
	
}
