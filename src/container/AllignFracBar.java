package container;

import display.rectangle;

import tree.CompoundTerm;

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
			CompoundTerm kid = tr.getChildren().get(i);
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
			CompoundTerm kid = tr.getChildren().get(i);
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
			CompoundTerm kid = tr.getChildren().get(i);
			kid.getContainer().bl.x = xsofar;
			xsofar+= kid.getContainer().width;
		}


		//find biggest height overall
		float ysofar = 0;
		for(int i = 0; i<tr.getChildren().size(); i++){
			CompoundTerm kid = tr.getChildren().get(i);
			if((kid.getContainer().height + kid.getContainer().bl.y)> ysofar){
				ysofar = kid.getContainer().height+ kid.getContainer().bl.y;
			}
		}
		
		rectangle thiscont = new rectangle();
		thiscont.width = xsofar;
		thiscont.height = ysofar;
		
		tr.setContainer(thiscont);
	}
	
}
