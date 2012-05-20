package container;

import display.Rectangle;

import tree.Term;
import tree.compound.CompoundTerm;

public class AllignFracBar {

	public AllignFracBar(CompoundTerm tr){
		//check to see that all the kids are containered
		boolean allgood = true;
		
		for(int i = 0; i<tr.getChildren().size(); i++){
			if(tr.getChildren().get(i).getContainer() == null){
				allgood = false;
			}
		}
		
		// if all are containered, find highest barheight
		float barheight =0;
		for(int i = 0; i<tr.getChildren().size(); i++){
			Term kid = tr.getChildren().get(i);
		/*	if(kid.getFunction() instanceof Divide && kid.getChildren().size() == 3){
				if(kid.getChildren().get(2).getContainer().height>barheight){
					barheight = kid.getChildren().get(2).getContainer().height;
				}
			}
			else{
				if(kid.getContainer().height/2>barheight){
					barheight = kid.getContainer().height/2;
				}
			}*/
		}

		//allign y's
		for(int i = 0; i<tr.getChildren().size(); i++){
			Term kid = tr.getChildren().get(i);
	/*		if(kid.getFunction() instanceof Divide && kid.getChildren().size() == 3){
				kid.getContainer().bl.y = barheight - kid.getChildren().get(2).getContainer().height;
			}
			else{
				kid.getContainer().bl.y = barheight - kid.getContainer().height/2;
			}*/
		}
		
		//allign x's
		float xsofar = 0;
		for(int i = 0; i<tr.getChildren().size(); i++){
			Term kid = tr.getChildren().get(i);
			kid.getContainer().bl.x = xsofar;
			xsofar+= kid.getContainer().getWidth();
		}


		//find biggest height overall
		float ysofar = 0;
		for(int i = 0; i<tr.getChildren().size(); i++){
			Term kid = tr.getChildren().get(i);
			if((kid.getContainer().getHeight() + kid.getContainer().bl.y)> ysofar){
				ysofar = kid.getContainer().getHeight() + kid.getContainer().bl.y;
			}
		}
		
		Rectangle thiscont = new Rectangle();
		thiscont.setWidth(xsofar);
		thiscont.setHeight(ysofar);
		
		tr.setContainer(thiscont);
	}
	
}
