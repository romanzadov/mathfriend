package tree.operators;
import java.util.ArrayList;

import move.identify.FindSel;
import move.identify.ReturnSel;
import move.operators.PlusMove;
import representTerms.Image;
import tree.Term;
import display.point;
import display.rectangle;
//import android.util.Log;

public class Plus extends Operator {

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

		FindSel fs = new FindSel();

		ArrayList<Integer> key = fs.FindSelected(im.tr, sel);
		ReturnSel rs = new ReturnSel();
		Term secondsel = rs.Return(second, key);
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
		
		PlusMove pm = new PlusMove();
		Image Ghost = new Image();
		if(!(sel.parent.parent.getChilds().get(IntermIndex) instanceof Operator)){
			Ghost = pm.overEquals(im, sel, IntermIndex, xsel);}
		
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
}
