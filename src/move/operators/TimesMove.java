package move.operators;

import java.util.ArrayList;

import display.point;

import move.identify.TermMath;
import representTerms.Images;
import tree.Term;
import tree.functions.*;

public class TimesMove {

	public Images inTermMoves(Images im, Term sel,
			int IntermIndex) {
		Term second = null;
		try {
			 second = (Term) im.tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		ArrayList<Integer> key = TermMath.findTreePositionOfSelected(im.tr, sel);
		Term tomove = TermMath.findTermUsingKey(second, key);
//		ColorText ct = new ColorText(tomove, Color.red);
		Term parent = tomove.getParent();
		Term switched = tomove.getParent().getChildren().get(IntermIndex);
		int spottomove = tomove.getParent().getChildren().indexOf(tomove);
		
		if(spottomove == 0 && (IntermIndex != parent.getChildren().size()-1)){
			
			Term times = parent.getChildren().get(1);
			parent.getChildren().remove(0);
			parent.getChildren().remove(0);
			parent.getChildren().add(IntermIndex,times);
			parent.getChildren().add(IntermIndex,tomove);
			
		}
		else if(IntermIndex == 0){
			
			Term times = parent.getChildren().get(spottomove-1);
			parent.getChildren().remove(spottomove-1);
			parent.getChildren().remove(spottomove-1);
			parent.getChildren().add(0, times);
			parent.getChildren().add(0, tomove);
			
		}
		else if(spottomove == 0 && IntermIndex == parent.getChildren().size()-1){
			Term times = parent.getChildren().get(spottomove+1);
			parent.getChildren().remove(spottomove);
			parent.getChildren().remove(spottomove);
			parent.getChildren().add(times);
			parent.getChildren().add( tomove);
		}
		else if(IntermIndex == parent.getChildren().size()-1){
			Term times = parent.getChildren().get(spottomove-1);
			parent.getChildren().remove(spottomove-1);
			parent.getChildren().remove(spottomove-1);
			parent.getChildren().add(times);
			parent.getChildren().add( tomove);
		}
		else{
			Term times = parent.getChildren().get(spottomove-1);
			parent.getChildren().remove(spottomove-1);
			parent.getChildren().remove(spottomove-1);
			parent.getChildren().add(IntermIndex, times);
			parent.getChildren().add(IntermIndex, tomove);
		}
		
		
		
//		RelativeContainer dn = new RelativeContainer();
//		dn.drawelement(second, (int)im.tr.font);
		 
	

		Images Ghost = new Images(second.toString(), new point(im.bel.x,im.bel.y+(int) im.tr.getContainer().height/2+100));
		Ghost = new Images(Ghost.tr.toString(), Ghost.bel);
		return Ghost;
	}

	public Images overEqualsMoves(Images im,
			Term sel, int IntermIndex, double xsel) {
		
		
		
		Images Ghost = null;
/*
		boolean selfraction =  false;
		if(sel.getFunction() instanceof Times || sel.getFunction() instanceof Divide){
			if(sel.getChildren().size() == 3){
				if(sel.getChildren().get(1) instanceof Divide){
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

			Term top = recip.getChildren().get(0);
			recip.getChildren().set(0, recip.getChildren().get(2));
			recip.getChildren().set(2, top);
			
			regular.setParent(ghostsel.getParent());
			recip.setParent(ghostsel.getParent());
		}
		
		if(!selfraction){
			
			Term mid = new Term();
			mid.setParent(ghostsel.getParent());
			Number one = new Number(1);
			Divide div = new Divide();
			mid.getChildren().add(one);
			mid.getChildren().add(div);
			mid.getChildren().add(recip);
			mid.setFunction(div);
			
			one.setParent(div.setParent(recip.setParent(mid)));
			
			recip = mid;
			regular.setParent(ghostsel.getParent());
			
			
		}
		
			timesRecip(second, recip, ghostsel);
			
			
//			RelativeContainer dt = new RelativeContainer();
//			dt.drawelement(second, (int)im.tr.font);
			
			Ghost = new Image(second.toString(), new point(im.bel.x,im.bel.y+(int) im.tr.getContainer().height/2+100));
			Ghost = new Image(Ghost.tr.toString(), Ghost.bel);
		
		
	*/
		
		return Ghost;
	}

	public void timesRecip(Term second, Term recip, Term regular){
		TimesTerm TM = new TimesTerm();
		
		//start with out own side
		
			//remove the term being moved
		int posregular = regular.getParent().getChildren().indexOf(regular);
		
		int eqindex =0;
		try {
			eqindex = recip.getParent().getParent().getParent().getChildren().indexOf(recip.getParent().getParent());
		} catch (Exception e1) {}
		
		int eqindex2 = regular.getParent().getParent().getChildren().indexOf(regular.getParent());
		
		if(posregular== 0){
			regular.getParent().getChildren().remove(0);
			regular.getParent().getChildren().remove(0);
		}
		else{
			regular.getParent().getChildren().remove(posregular-1);
			regular.getParent().getChildren().remove(posregular-1);
		}
		
	
		
	/*	if(recip.getParent().getParent().getFunction() instanceof Plus || recip.getParent().getParent().getFunction() instanceof Minus){
			for(int i = 0; i< recip.getParent().getParent().getChildren().size(); i++){
				Term kid = recip.getParent().getParent().getChildren().get(i);
				if(!(kid instanceof Function) && (kid != recip.getParent())){
					
					Term clrecip = new Term();
					try {
						clrecip = (Term) recip.clone();
					} catch (CloneNotSupportedException e) {}
		//			ColorText ct = new ColorText(clrecip, Color.red);
					TM.Times(kid, clrecip);
					
				}
			}
		}*/
		
		if(regular.getParent().getChildren().size()==1){
			Term top = regular.getParent().getParent();
			Term bottom = regular.getParent().getChildren().get(0);
			int place = top.getChildren().indexOf(bottom.getParent());
			bottom.setParent(top);
			top.getChildren().set(place, bottom);
		}
		
		// next, do the other side of the equals
		if(recip.getParent().getParent().getParent() !=null && recip.getParent().getParent().getParent().getFunction() instanceof Equals){
			Term eq = recip.getParent().getParent().getParent();
			for(int i = 0; i<eq.getChildren().size(); i++){
				if(!(eq.getChildren().get(i) instanceof Function)&&i!=eqindex){
					Term recipcl =null;
					try {
						recipcl = (Term)recip.clone();
					} catch (CloneNotSupportedException e) {}
	//				ColorText ct = new ColorText(recipcl, Color.red);
					TM.Times(eq.getChildren().get(i), recipcl);
				}
			}
		}
		
		else if(regular.getParent().getParent() !=null && regular.getParent().getParent().getFunction() instanceof Equals){
			
			Term eq = regular.getParent().getParent();
			for(int i = 0; i<eq.getChildren().size(); i++){
				if(!(eq.getChildren().get(i) instanceof Function)&&i!=eqindex2){
					Term recipcl =null;
					try {
						recipcl = (Term)recip.clone();
					} catch (CloneNotSupportedException e) {}
	//				ColorText ct = new ColorText(recipcl, Color.red);
					TM.Times(eq.getChildren().get(i), recipcl);
				}
			}
		}
	}
	
}
