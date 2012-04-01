package container;

import java.util.ArrayList;

import representTerms.Image;
import representTerms.StringRectangle;
import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
import display.point;

public class AbsoluteContainer implements TreeFunction{

	int j = 0;
	ArrayList<StringRectangle> sr = new ArrayList<StringRectangle>();
	
	
	public ArrayList<StringRectangle> multiply(Image im, int fontsize, point bel){
		
			sr = im.relativeContainers;
			
			
			for(int i = 0; i< sr.size(); i++){
				
				sr.get(i).container.bl.y *= -fontsize;
				sr.get(i).container.bl.x *= fontsize;
				sr.get(i).container.width *=fontsize;
				sr.get(i).container.height *= fontsize;
				
				sr.get(i).container.bl.x += bel.x;
				sr.get(i).container.bl.y += bel.y;
				
				
				//container.bl.x += 100;
			//	sr.get(i).container.bl.y +=200;
				
				
			}
			
		downwalk dw = new downwalk(im.tr, this);	
		
		
		return sr;
	}

	public void performAction(Term tr) {
		tr.container = sr.get(j).container;
		j++;
		
	}
	
}
