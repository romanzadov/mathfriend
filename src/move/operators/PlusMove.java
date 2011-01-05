package move.operators;

import java.util.ArrayList;

import move.identify.TermMath;
import move.identify.ReturnSel;
import representTerms.Image;
import tree.Term;
import tree.operators.Minus;
import tree.operators.Operator;
import tree.operators.Plus;
//import android.util.Log;
import display.point;
import display.rectangle;

public class PlusMove {

	static final String TAG = "plusmove";

	public static Image overEquals(Image im, Term sel, int equalsIndex, double xsel){
		//path pa = new path();
		//term second = pa.pathway(im.ft.originalstring);
		Term second = new Term();
		try {
			second = (Term)im.tr.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		ArrayList<Integer> key = TermMath.findTreePositionOfSelected(im.tr, sel);
		Term holder = TermMath.findTermUsingKey(second, key);

//		ColorText ct = new ColorText(holder, Color.red);

		Term moveto = holder.parent.parent.getChilds().get(equalsIndex);
		key = TermMath.findTreePositionOfSelected(second, moveto);
		Term originalmoveto = TermMath.findTermUsingKey(im.tr, key);

		int selindex = holder.parent.getChilds().indexOf(holder);
		Operator op =  null;
		Term mid = new Term();

//		Log.d(TAG, "moveto: "+moveto+" selindex: "+selindex+ " sel: "+sel+" equalsIndex: "+equalsIndex);
		
		if(!(moveto.operator instanceof Plus)&&!(moveto.operator instanceof Minus)){
			Plus pl = new Plus();
			int num = second.getChilds().indexOf(moveto);
			mid.parent = moveto.parent;
			mid.operator = pl;
			mid.getChilds().add(moveto);
			moveto.parent = mid;
			second.getChilds().set(num, mid);
			moveto = mid;
		
		}

		if(selindex > 0){ 
			
			op = (Operator)holder.parent.getChilds().get(selindex-1);
			holder.parent.getChilds().remove(selindex-1);
			holder.parent.getChilds().remove(selindex-1);
			if((moveto.operator instanceof Plus)||(moveto.operator instanceof Minus)){
				if(op instanceof Plus){op = new Minus();}
				else if(op instanceof Minus){op = new Plus();}
				op.parent = moveto;
				holder.parent = moveto;
				moveto.getChilds().add(op);
				moveto.getChilds().add(holder);
			}
		}
		if(selindex == 0 ){

			if(holder.parent.getChilds().get(1) instanceof Minus){
				holder.parent.getChilds().set(2, holder.parent.getChilds().get(2).toggleNegative());
			}

			if(holder.isNegative()){
				holder = holder.toggleNegative();
				holder.parent.getChilds().remove(0);
				holder.parent.getChilds().remove(0);

				Plus pl = new Plus();
				pl.parent = moveto;
				holder.parent = moveto;
				moveto.getChilds().add(pl);
				moveto.getChilds().add(holder);
			}
			else{
				Minus mn = new Minus();
				holder.parent.getChilds().remove(0);
				holder.parent.getChilds().remove(0);
				mn.parent = moveto;

				holder.parent = moveto;
				moveto.getChilds().add(mn);
				moveto.getChilds().add(holder);
			}

		}
		
			inTermFocus(originalmoveto, moveto, holder, xsel);
		
		


	//	RelativeContainer dn = new RelativeContainer();
	//	dn.drawelement(second, (int)im.tr.font);
		 
	

		Image Ghost = new Image(second.toString(), new point(im.bel.x,im.bel.y+(int)im.tr.container.height/2+100));
		Ghost = new Image(Ghost.tr.toString(), Ghost.bel);
		return Ghost;


	}

	private static void inTermFocus(Term originalmoveto, Term moveto, Term holder, double xsel){

		int IntermIndex = Integer.MAX_VALUE;
		Plus Plus = new Plus();

		for(int i = 0; i< originalmoveto.getChilds().size(); i++){
			rectangle container =originalmoveto.getChilds().get(i).container;
			if((xsel - container.bl.x)>=0 && (xsel-container.bl.x)<=container.width){
				IntermIndex = i;
				break;
			}

		}

		if(IntermIndex != Integer.MAX_VALUE){
			if(moveto.getChilds().size()==1){

			}
			else if(IntermIndex != originalmoveto.getChilds().size()-1){


				if(!(originalmoveto.getChilds().get(IntermIndex) instanceof Operator)){
					Plus.changeterm(holder, IntermIndex);
				}
				else{	
					if(IntermIndex<originalmoveto.getChilds().size()-1){
						Plus.changeterm(holder, IntermIndex+1);
					}	
				}
			}		



		}
	}

	public static Term removeAdditiveChild(Term main, int positionOfChild){
		
		//check inputs
		Operator op = main.operator;
		if(op instanceof Plus || op instanceof Minus){}
		else{ System.out.println("remove of addition run on a term without addition. In PlusMove. ");  return null; }
		
		
		Term result = null;
		
		try {
			result = (Term) main.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(positionOfChild == 0){
			//first remove the term
			result.getChilds().remove(0);
			//then remove the plus or minus after it and change the sign of the next term appropriately
			Term operator = result.getChilds().get(0);
			if(operator instanceof Plus){ result.getChilds().remove(0); }
			else if (operator instanceof Minus){
				result.getChilds().remove(0);
				result.getChilds().get(0).toggleNegative();
			}
			else{
				System.out.println("remove of addition run on a term without addition. In PlusMove. ");  return null;
			}
			
		}
		
		else{
			// remove the term and the sign in front of it
			result.getChilds().remove(positionOfChild - 1);
			result.getChilds().remove(positionOfChild - 1);
		}
		
		return result;
	}

	public static Term replaceAdditiveTerm(Term main, Term sum, int positionOfReplacement){
		
		//check inputs
		Operator op = main.operator;
		boolean checksout = true;
		if(op instanceof Plus || op instanceof Minus){ checksout = false;}
		else{ System.out.println("replace addition run on a term without addition. In PlusMove. ");  return null; }
		
		Term result = null;
		try {
			 result = (Term) main.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(positionOfReplacement == 0 && result.getChilds().size()>0){
			result.getChilds().set(0, sum);
		}
		
		else{
			
			if(sum.isNegative()){
				Minus minus = new Minus();
				minus.parent = result;
				result.getChilds().set(positionOfReplacement-1, minus);
				
				sum.toggleNegative();
				sum.parent = result;
				result.getChilds().set(positionOfReplacement, sum);
			}
			else{
			
				Plus plus = new Plus();
				plus.parent = result;
				result.getChilds().set(positionOfReplacement-1, plus);
				
				sum.parent = result;
				result.getChilds().set(positionOfReplacement, sum);
				
			}
			
		}
		
		return result;
	}
	
	
}

