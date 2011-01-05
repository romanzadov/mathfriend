package tree.operators;
import java.util.ArrayList;

import javax.print.attribute.standard.Fidelity;

import move.identify.TermMath;
import move.identify.ReturnSel;
import move.operators.PlusMove;
import representTerms.Image;
import tree.Term;
import tree.simple.Number;
import tree.simple.Constant;
import tree.simple.variable;
import display.point;
import display.rectangle;
//import android.util.Log;

public class Plus extends Operator{

	public boolean invisible;

	static final String TAG = "plus";

	public Plus(){
		inputs =2;
		invertable=true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=0;
		orderofoperation=5;
		invisible = false;
		lmult = false;
		rmult = false;
		todraw = "+";
		valuestring = "+";
	}
	//draws the rectangle that goes around terms with a plus operator
	public rectangle giverect(Term tr){
		rectangle a = new rectangle();

		if(tr.getChilds().size()==0){

			if(tr instanceof Plus){
				a.height = 1; 
				a.width = 1;
				tr.todraw = "+";
				tr.container = a;}
			if(tr instanceof Minus){
				a.height = 1;
				a.width = 1;
				tr.todraw = "-";
				tr.container = a;
			}
		}
		else if(tr.getChilds().size()>=1){
			//check rectangled and get max height
			float maxheight = 0;
			for(int i =0; i<tr.getChilds().size();i++){
				if(tr.getChilds().get(i).container==null){
					System.out.println("error: plus called on non-rectangled");
				}
				if(tr.getChilds().get(i).container.height>maxheight){
					maxheight = tr.getChilds().get(i).container.height;
				}
			}
			float xsofar = 0;
			for(int i =0; i<tr.getChilds().size();i++){
				rectangle left = tr.getChilds().get(i).container;
				left.bl.y = maxheight/2-left.height/2;
				left.bl.x = xsofar;
				xsofar = left.bl.x+left.width;
			}
			a.height = maxheight;
			a.width = xsofar;
		}


		return a;
	}

	public rectangle justplus(Term tr){
		rectangle a = new rectangle();
		a.width = 1;
		a.height = 1;
		tr.todraw = "+";
		tr.container = a;
		return a;
	}
	public rectangle justminus(Term tr){
		rectangle a = new rectangle();
		a.width = 1;
		a.height = 1;
		tr.todraw = "-";
		tr.container = a;
		return a;
	}

	public double function(double a, double b)
	{
		double c = a + b;
		return c;
	}

	public Image inTermMoves(Image im, Term sel, int IntermIndex){

		Term second = new Term();
		try {
			second = (Term)im.tr.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Integer> key = TermMath.findTreePositionOfSelected(im.tr, sel);
		Term secondsel = TermMath.findTermUsingKey(second, key);
		
		if(!secondsel.parent.hasparen){
			changeterm(secondsel, IntermIndex);
		}
		else{

		}

		//	RelativeContainer dn = new RelativeContainer();
		//	dn.drawelement(second, (int)im.tr.font);
		Image Ghost = new Image(second.toString(), new point(im.bel.x,im.bel.y+(int)im.tr.container.height/2+100));

		return Ghost;
	}

	public Image overEqualsMoves(Image im, Term sel, int IntermIndex, double xsel){

		
		Image Ghost = new Image();
		if(!(sel.parent.parent.getChilds().get(IntermIndex) instanceof Operator)){
			Ghost = PlusMove.overEquals(im, sel, IntermIndex, xsel);}

		return Ghost;
	}


	public void changeterm( Term sel, int IntermIndex){

		int selindex = sel.parent.getChilds().indexOf(sel);


		Term holder = sel;
		Term moveto = sel.parent.getChilds().get(IntermIndex);
		//	ColorText CT = new ColorText(holder, Color.red);




		if(selindex != IntermIndex){
			if(sel.parent.hasparen){IntermIndex++; selindex++;}  // in parens still doesn't work

			if(selindex == 0 ){
				if(holder.isNegative()){
					holder = holder.toggleNegative();
					Minus mn = new Minus();
					mn.parent = holder.parent;
					//				mn.wordcolor = Color.red;
					holder.parent.getChilds().add(IntermIndex+1, holder);
					holder.parent.getChilds().add(IntermIndex+1, mn);
				}
				else{
					Plus pl = new Plus();
					pl.parent = holder.parent;
					//				pl.wordcolor = Color.red;
					holder.parent.getChilds().add(IntermIndex+1, holder);
					holder.parent.getChilds().add(IntermIndex+1, pl);
				}
				holder.parent.getChilds().remove(0);
				Term op = holder.parent.getChilds().get(0);
				if(op instanceof Plus){
					holder.parent.getChilds().remove(0);
				}
				if(op instanceof Minus){
					holder.parent.getChilds().remove(0);
					holder.parent.getChilds().get(0).toggleNegative();
				}
			}
			else if(IntermIndex == 0){

				if(holder.parent.getChilds().get(selindex-1) instanceof Minus){
					Term mid = holder.toggleNegative();
					//			Log.d(TAG, "mid: "+mid);
					mid.parent = holder.parent;
					holder = mid;
					//			Log.d(TAG, "holder: "+holder);
				}
				//		Log.d(TAG, "parent: "+holder.parent+" hold: "+holder+" selindex: "+selindex);

				holder.parent.getChilds().remove(selindex-1);
				holder.parent.getChilds().remove(selindex-1);

				if(moveto.isNegative()){
					moveto.toggleNegative();
					Minus mn = new Minus();
					mn.parent = holder.parent;
					holder.parent.getChilds().add(0,mn);
				}
				else{
					Plus pl = new Plus();
					pl.parent = holder.parent;
					holder.parent.getChilds().add(0, pl);
				}
				holder.parent.getChilds().add(0, holder);


			}
			else{
				Term op = holder.parent.getChilds().get(selindex-1);
				holder.parent.getChilds().remove(selindex-1);
				holder.parent.getChilds().remove(selindex-1);
				holder.parent.getChilds().add(IntermIndex-1, holder);
				holder.parent.getChilds().add(IntermIndex-1, op);
				//			op.wordcolor = Color.red;
			}

		}

	}
	@Override
	public Term simpleOperation(Term term) {
		

		//find first two numbers that can be added and add them.
		Term resultingTerm = null;
		Number firstAddent = null;
		double firstValue = 0;
		boolean done = false;
		for(int i = 0; i<term.getChilds().size(); i++){
			if(!done){
				
				Term child = term.getChilds().get(i);
				if(child instanceof Number){
					if(firstAddent == null){
						firstAddent = (Number) child;
						firstValue = firstAddent.getValue();
						if( i>0 && (term.getChilds().get(i-1) instanceof Minus)){
							firstValue *= -1;
						}
					}
					else{
						Number secondAddent = (Number) child;
						double secondValue = secondAddent.getValue();
						if(i>0 && term.getChilds().get(i-1) instanceof Minus){
							secondValue *=-1;
						}
						Number result = new Number(firstValue + secondValue);
						
						
						try {
							 resultingTerm = (Term) term.clone();
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//remove the second addent and replace the first addent with our result
						resultingTerm = PlusMove.removeAdditiveChild(resultingTerm, term.getChilds().indexOf(secondAddent));
						
						resultingTerm = PlusMove.replaceAdditiveTerm(resultingTerm, result, term.getChilds().indexOf(firstAddent));
						
						done = true;
					//	System.out.println("first:"+firstValue+ " second: "+secondValue+" result: "+result+" rt: "+resultingTerm);
					}
				}
			}
		}
		
		return resultingTerm;
	}
}
