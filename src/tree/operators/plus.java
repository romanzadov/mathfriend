package tree.operators;
import java.util.ArrayList;

import move.identify.FindSel;
import move.identify.ReturnSel;
import move.operators.plusmove;
import representTerms.image;
import tree.term;
import display.point;
import display.rectangle;
//import android.util.Log;

public class plus extends operator{

	public boolean invisible;

	static final String TAG = "plus";
	
	public plus(){
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
	public rectangle giverect(term tr){
		rectangle a = new rectangle();

		if(tr.getChilds().size()==0){

			if(tr instanceof plus){
				a.height = 1; 
				a.width = 1;
				tr.todraw = "+";
				tr.container = a;}
			if(tr instanceof minus){
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

	public rectangle justplus(term tr){
		rectangle a = new rectangle();
		a.width = 1;
		a.height = 1;
		tr.todraw = "+";
		tr.container = a;
		return a;
	}
	public rectangle justminus(term tr){
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

	public image inTermMoves(image im, term sel, int IntermIndex){

		term second = new term();
		try {
			second = (term)im.tr.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FindSel fs = new FindSel();

		ArrayList<Integer> key = fs.FindSelected(im.tr, sel);
		ReturnSel rs = new ReturnSel();
		term secondsel = rs.Return(second, key);
		if(!secondsel.parent.hasparen){
			changeterm(secondsel, IntermIndex);
		}
		else{
			
		}

	//	RelativeContainer dn = new RelativeContainer();
	//	dn.drawelement(second, (int)im.tr.font);
		image Ghost = new image(second.toString(), new point(im.bel.x,im.bel.y+(int)im.tr.container.height/2+100));

		return Ghost;
	}

	public image overEqualsMoves(image im, term sel, int IntermIndex, double xsel){
		
		plusmove pm = new plusmove();
		image Ghost = new image();
		if(!(sel.parent.parent.getChilds().get(IntermIndex) instanceof operator)){
			Ghost = pm.overEquals(im, sel, IntermIndex, xsel);}
		
		return Ghost;
	}


	public void changeterm( term sel, int IntermIndex){
	
		int selindex = sel.parent.getChilds().indexOf(sel);


		term holder = sel;
		term moveto = sel.parent.getChilds().get(IntermIndex);
	//	ColorText CT = new ColorText(holder, Color.red);
		



		if(selindex != IntermIndex){
			if(sel.parent.hasparen){IntermIndex++; selindex++;}  // in parens still doesn't work

			if(selindex == 0 ){
				if(holder.isNegative()){
					holder = holder.toggleNegative();
					minus mn = new minus();
					mn.parent = holder.parent;
	//				mn.wordcolor = Color.red;
					holder.parent.getChilds().add(IntermIndex+1, holder);
					holder.parent.getChilds().add(IntermIndex+1, mn);
				}
				else{
					plus pl = new plus();
					pl.parent = holder.parent;
	//				pl.wordcolor = Color.red;
					holder.parent.getChilds().add(IntermIndex+1, holder);
					holder.parent.getChilds().add(IntermIndex+1, pl);
				}
				holder.parent.getChilds().remove(0);
				term op = holder.parent.getChilds().get(0);
				if(op instanceof plus){
					holder.parent.getChilds().remove(0);
				}
				if(op instanceof minus){
					holder.parent.getChilds().remove(0);
					holder.parent.getChilds().get(0).toggleNegative();
				}
			}
			else if(IntermIndex == 0){

				if(holder.parent.getChilds().get(selindex-1) instanceof minus){
					term mid = holder.toggleNegative();
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
					minus mn = new minus();
					mn.parent = holder.parent;
					holder.parent.getChilds().add(0,mn);
				}
				else{
					plus pl = new plus();
					pl.parent = holder.parent;
					holder.parent.getChilds().add(0, pl);
				}
				holder.parent.getChilds().add(0, holder);


			}
			else{
				term op = holder.parent.getChilds().get(selindex-1);
				holder.parent.getChilds().remove(selindex-1);
				holder.parent.getChilds().remove(selindex-1);
				holder.parent.getChilds().add(IntermIndex-1, holder);
				holder.parent.getChilds().add(IntermIndex-1, op);
	//			op.wordcolor = Color.red;
			}

		}

	}
}
