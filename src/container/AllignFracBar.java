package container;

import display.rectangle;

import tree.Term;
import tree.operators.Divide;

public class AllignFracBar {

	public AllignFracBar(Term tr){
		//check to see that all the kids are containered
		boolean allgood = true;
		
		for(int i = 0; i<tr.getChildren().size(); i++){
			if(tr.getChildren().get(i).container == null){
				allgood = false;
			}
		}
		
		// if all are containered, find highest barheight
		float barheight =0;
		for(int i = 0; i<tr.getChildren().size(); i++){
			Term kid = tr.getChildren().get(i);
			if(kid.operator instanceof Divide && kid.getChildren().size() == 3){
				if(kid.getChildren().get(2).container.height>barheight){
					barheight = kid.getChildren().get(2).container.height;
				}
			}
			else{
				if(kid.container.height/2>barheight){
					barheight = kid.container.height/2;
				}
			}
		}

		//allign y's
		for(int i = 0; i<tr.getChildren().size(); i++){
			Term kid = tr.getChildren().get(i);
			if(kid.operator instanceof Divide && kid.getChildren().size() == 3){
				kid.container.bl.y = barheight - kid.getChildren().get(2).container.height;
			}
			else{
				kid.container.bl.y = barheight - kid.container.height/2;
			}
		}
		
		//allign x's
		float xsofar = 0;
		for(int i = 0; i<tr.getChildren().size(); i++){
			Term kid = tr.getChildren().get(i);
			kid.container.bl.x = xsofar;
			xsofar+=kid.container.width;
		}


		//find biggest height overall
		float ysofar = 0;
		for(int i = 0; i<tr.getChildren().size(); i++){
			Term kid = tr.getChildren().get(i);
			if((kid.container.height + kid.container.bl.y)> ysofar){
				ysofar = kid.container.height+kid.container.bl.y;
			}
		}
		
		rectangle thiscont = new rectangle();
		thiscont.width = xsofar;
		thiscont.height = ysofar;
		
		tr.container = thiscont;
	}
	
}
