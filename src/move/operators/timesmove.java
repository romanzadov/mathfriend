package move.operators;

import java.util.ArrayList;

import display.point;

import move.identify.TermMath;
import move.identify.ReturnSel;
import representTerms.Image;
import tree.Term;
import tree.operators.Divide;
import tree.operators.Equals;
import tree.operators.Minus;
import tree.operators.Operator;
import tree.operators.Plus;
import tree.operators.Times;
import tree.simple.Number;

public class TimesMove {

	public Image inTermMoves(Image im, Term sel,
			int IntermIndex) {
		Term second = null;
		try {
			 second = (Term) im.tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		ArrayList<Integer> key = TermMath.findTreePositionOfSelected(im.tr, sel);
		Term tomove = TermMath.findTermUsingKey(second, key);
//		ColorText ct = new ColorText(tomove, Color.red);
		Term parent = tomove.parent;
		Term switched = tomove.parent.getChilds().get(IntermIndex);
		int spottomove = tomove.parent.getChilds().indexOf(tomove);
		
		if(spottomove == 0 && (IntermIndex != parent.getChilds().size()-1)){
			
			Term times = parent.getChilds().get(1);
			parent.getChilds().remove(0);
			parent.getChilds().remove(0);
			parent.getChilds().add(IntermIndex,times);
			parent.getChilds().add(IntermIndex,tomove);
			
		}
		else if(IntermIndex == 0){
			
			Term times = parent.getChilds().get(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().add(0, times);
			parent.getChilds().add(0, tomove);
			
		}
		else if(spottomove == 0 && IntermIndex == parent.getChilds().size()-1){
			Term times = parent.getChilds().get(spottomove+1);
			parent.getChilds().remove(spottomove);
			parent.getChilds().remove(spottomove);
			parent.getChilds().add(times);
			parent.getChilds().add( tomove);
		}
		else if(IntermIndex == parent.getChilds().size()-1){
			Term times = parent.getChilds().get(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().add(times);
			parent.getChilds().add( tomove);
		}
		else{
			Term times = parent.getChilds().get(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().remove(spottomove-1);
			parent.getChilds().add(IntermIndex, times);
			parent.getChilds().add(IntermIndex, tomove);
		}
		
		
		
//		RelativeContainer dn = new RelativeContainer();
//		dn.drawelement(second, (int)im.tr.font);
		 
	

		Image Ghost = new Image(second.toString(), new point(im.bel.x,im.bel.y+(int)im.tr.container.height/2+100));
		Ghost = new Image(Ghost.tr.toString(), Ghost.bel);
		return Ghost;
	}

	public Image overEqualsMoves(Image im,
			Term sel, int IntermIndex, double xsel) {
		
		
		
		Image Ghost = null;
		
		boolean selfraction =  false;
		if(sel.operator instanceof Times || sel.operator instanceof Divide){
			if(sel.getChilds().size() == 3){
				if(sel.getChilds().get(1) instanceof Divide){
					selfraction = true;
				}
			}
		}
		
		Term second = new Term();
		try {
			second = (Term) im.tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		TermMath fs = new TermMath();
		ArrayList <Integer> key = fs.findTreePositionOfSelected(im.tr, sel);
		ReturnSel rs = new ReturnSel();
		
		Term ghostsel = rs.findTermUsingKey(second, key);
		
		Term regular = new Term();
		try {
			regular = (Term) ghostsel.clone();
		} catch (CloneNotSupportedException e) {}
		
		Term recip = new Term();
		
		try {
			recip = (Term) regular.clone();
		} catch (CloneNotSupportedException e) {}
		
		if(selfraction){

			Term top = recip.getChilds().get(0);
			recip.getChilds().set(0, recip.getChilds().get(2));
			recip.getChilds().set(2, top);
			
			regular.parent = ghostsel.parent;
			recip.parent = ghostsel.parent;
		}
		
		if(!selfraction){
			
			Term mid = new Term();
			mid.parent = ghostsel.parent;
			Number one = new Number(1);
			Divide div = new Divide();
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
			
			Ghost = new Image(second.toString(), new point(im.bel.x,im.bel.y+(int)im.tr.container.height/2+100));
			Ghost = new Image(Ghost.tr.toString(), Ghost.bel);
		
		
	
		
		return Ghost;
	}

	public void timesRecip(Term second, Term recip, Term regular){
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
		
	
		
		if(recip.parent.parent.operator instanceof Plus || recip.parent.parent.operator instanceof Minus){
			for(int i = 0; i<recip.parent.parent.getChilds().size(); i++){
				Term kid = recip.parent.parent.getChilds().get(i);
				if(!(kid instanceof Operator) && (kid != recip.parent)){
					
					Term clrecip = new Term();
					try {
						clrecip = (Term) recip.clone();
					} catch (CloneNotSupportedException e) {}
		//			ColorText ct = new ColorText(clrecip, Color.red);
					TM.Times(kid, clrecip);
					
				}
			}
		}
		
		if(regular.parent.getChilds().size()==1){
			Term top = regular.parent.parent;
			Term bottom = regular.parent.getChilds().get(0);
			int place = top.getChilds().indexOf(bottom.parent);
			bottom.parent = top;
			top.getChilds().set(place, bottom);
		}
		
		// next, do the other side of the equals
		if(recip.parent.parent.parent!=null && recip.parent.parent.parent.operator instanceof Equals){
			Term eq = recip.parent.parent.parent;
			for(int i = 0; i<eq.getChilds().size(); i++){
				if(!(eq.getChilds().get(i) instanceof Operator)&&i!=eqindex){
					Term recipcl =null;
					try {
						recipcl = (Term)recip.clone();
					} catch (CloneNotSupportedException e) {}
	//				ColorText ct = new ColorText(recipcl, Color.red);
					TM.Times(eq.getChilds().get(i), recipcl);
				}
			}
		}
		
		else if(regular.parent.parent!=null && regular.parent.parent.operator instanceof Equals){
			
			Term eq = regular.parent.parent;
			for(int i = 0; i<eq.getChilds().size(); i++){
				if(!(eq.getChilds().get(i) instanceof Operator)&&i!=eqindex2){
					Term recipcl =null;
					try {
						recipcl = (Term)recip.clone();
					} catch (CloneNotSupportedException e) {}
	//				ColorText ct = new ColorText(recipcl, Color.red);
					TM.Times(eq.getChilds().get(i), recipcl);
				}
			}
		}
	}
	
}
