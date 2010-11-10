package move.operators;

import java.util.ArrayList;

import container.RelativeContainer;
import display.point;

import move.identify.FindSel;
import move.identify.ReturnSel;
import representTerms.image;
import tree.term;
import tree.operators.divide;
import tree.operators.equals;
import tree.operators.minus;
import tree.operators.operator;
import tree.operators.plus;
import tree.operators.times;
import tree.simple.Number;

public class timesmove {

	public image inTermMoves(image im, term sel,
			int IntermIndex) {
		term second = null;
		try {
			 second = (term) im.tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		FindSel fs = new FindSel();
		ArrayList<Integer> key = fs.FindSelected(im.tr, sel);
		ReturnSel rs = new ReturnSel();
		
		
		term tomove = rs.Return(second, key);
//		ColorText ct = new ColorText(tomove, Color.red);
		term parent = tomove.parent;
		term switched = tomove.parent.getChilds().get(IntermIndex);
		int spottomove = tomove.parent.getChilds().indexOf(tomove);
		
		if(spottomove == 0 && (IntermIndex != parent.getChilds().size()-1)){
			
			term times = parent.getChilds().get(1);
			parent.getChilds().remove(0);
			parent.getChilds().remove(0);
			parent.getChilds().add(IntermIndex,times);
			parent.getChilds().add(IntermIndex,tomove);
			
		}
		else if(IntermIndex == 0){
			
			term times = parent.getChilds().get(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().add(0, times);
			parent.getChilds().add(0, tomove);
			
		}
		else if(spottomove == 0 && IntermIndex == parent.getChilds().size()-1){
			term times = parent.getChilds().get(spottomove+1);
			parent.getChilds().remove(spottomove);
			parent.getChilds().remove(spottomove);
			parent.getChilds().add(times);
			parent.getChilds().add( tomove);
		}
		else if(IntermIndex == parent.getChilds().size()-1){
			term times = parent.getChilds().get(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().add(times);
			parent.getChilds().add( tomove);
		}
		else{
			term times = parent.getChilds().get(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().add(IntermIndex, times);
			parent.getChilds().add(IntermIndex, tomove);
		}
		
		
		
//		RelativeContainer dn = new RelativeContainer();
//		dn.drawelement(second, (int)im.tr.font);
		 
	

		image Ghost = new image(second.toString(), new point(im.bel.x,im.bel.y+(int)im.tr.container.height/2+100));
		Ghost = new image(Ghost.tr.toString(), Ghost.bel);
		return Ghost;
	}

	public image overEqualsMoves(image im,
			term sel, int IntermIndex, double xsel) {
		
		
		
		image Ghost = null;
		
		boolean selfraction =  false;
		if(sel.operator instanceof times || sel.operator instanceof divide){
			if(sel.getChilds().size() == 3){
				if(sel.getChilds().get(1) instanceof divide){
					selfraction = true;
				}
			}
		}
		
		term second = new term();
		try {
			second = (term) im.tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		FindSel fs = new FindSel();
		ArrayList <Integer> key = fs.FindSelected(im.tr, sel);
		ReturnSel rs = new ReturnSel();
		
		term ghostsel = rs.Return(second, key);
		
		term regular = new term();
		try {
			regular = (term) ghostsel.clone();
		} catch (CloneNotSupportedException e) {}
		
		term recip = new term();
		
		try {
			recip = (term) regular.clone();
		} catch (CloneNotSupportedException e) {}
		
		if(selfraction){

			term top = recip.getChilds().get(0);
			recip.getChilds().set(0, recip.getChilds().get(2));
			recip.getChilds().set(2, top);
			
			regular.parent = ghostsel.parent;
			recip.parent = ghostsel.parent;
		}
		
		if(!selfraction){
			
			term mid = new term();
			mid.parent = ghostsel.parent;
			Number one = new Number(1);
			divide div = new divide();
			mid.getChilds().add(one);
			mid.getChilds().add(div);
			mid.getChilds().add(recip);
			mid.operator = div;
			
			one.parent = div.parent = recip.parent = mid;
			
			recip = mid;
			regular.parent = ghostsel.parent;
			
			
		}
		
			timesRecip(second, recip, ghostsel);
			
			
//			RelativeContainer dt = new RelativeContainer();
//			dt.drawelement(second, (int)im.tr.font);
			
			Ghost = new image(second.toString(), new point(im.bel.x,im.bel.y+(int)im.tr.container.height/2+100));
			Ghost = new image(Ghost.tr.toString(), Ghost.bel);
		
		
	
		
		return Ghost;
	}

	public void timesRecip(term second, term recip, term regular){
		TimesTerm TM = new TimesTerm();
		
		//start with out own side
		
			//remove the term being moved
		int posregular = regular.parent.getChilds().indexOf(regular);
		
		int eqindex =0;
		try {
			eqindex = recip.parent.parent.parent.getChilds().indexOf(recip.parent.parent);
		} catch (Exception e1) {}
		
		int eqindex2 = regular.parent.parent.getChilds().indexOf(regular.parent);
		
		if(posregular== 0){
			regular.parent.getChilds().remove(0);
			regular.parent.getChilds().remove(0);
		}
		else{
			regular.parent.getChilds().remove(posregular-1);
			regular.parent.getChilds().remove(posregular-1);
		}
		
	
		
		if(recip.parent.parent.operator instanceof plus || recip.parent.parent.operator instanceof minus){
			for(int i = 0; i<recip.parent.parent.getChilds().size(); i++){
				term kid = recip.parent.parent.getChilds().get(i);
				if(!(kid instanceof operator) && (kid != recip.parent)){
					
					term clrecip = new term();
					try {
						clrecip = (term) recip.clone();
					} catch (CloneNotSupportedException e) {}
		//			ColorText ct = new ColorText(clrecip, Color.red);
					TM.Times(kid, clrecip);
					
				}
			}
		}
		
		if(regular.parent.getChilds().size()==1){
			term top = regular.parent.parent;
			term bottom = regular.parent.getChilds().get(0);
			int place = top.getChilds().indexOf(bottom.parent);
			bottom.parent = top;
			top.getChilds().set(place, bottom);
		}
		
		// next, do the other side of the equals
		if(recip.parent.parent.parent!=null && recip.parent.parent.parent.operator instanceof equals){
			term eq = recip.parent.parent.parent;
			for(int i = 0; i<eq.getChilds().size(); i++){
				if(!(eq.getChilds().get(i) instanceof operator)&&i!=eqindex){
					term recipcl =null;
					try {
						recipcl = (term)recip.clone();
					} catch (CloneNotSupportedException e) {}
	//				ColorText ct = new ColorText(recipcl, Color.red);
					TM.Times(eq.getChilds().get(i), recipcl);
				}
			}
		}
		
		else if(regular.parent.parent!=null && regular.parent.parent.operator instanceof equals){
			
			term eq = regular.parent.parent;
			for(int i = 0; i<eq.getChilds().size(); i++){
				if(!(eq.getChilds().get(i) instanceof operator)&&i!=eqindex2){
					term recipcl =null;
					try {
						recipcl = (term)recip.clone();
					} catch (CloneNotSupportedException e) {}
	//				ColorText ct = new ColorText(recipcl, Color.red);
					TM.Times(eq.getChilds().get(i), recipcl);
				}
			}
		}
	}
	
}
