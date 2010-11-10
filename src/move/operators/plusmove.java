package move.operators;

import java.util.ArrayList;

import move.identify.FindSel;
import move.identify.ReturnSel;
import representTerms.image;
import tree.term;
import tree.operators.minus;
import tree.operators.operator;
import tree.operators.plus;
//import android.util.Log;
import display.point;
import display.rectangle;

public class plusmove {

	static final String TAG = "plusmove";

	public image overEquals(image im, term sel, int equalsIndex, double xsel){
		//path pa = new path();
		//term second = pa.pathway(im.ft.originalstring);
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
		term holder = rs.Return(second, key);

//		ColorText ct = new ColorText(holder, Color.red);

		term moveto = holder.parent.parent.getChilds().get(equalsIndex);
		key = fs.FindSelected(second, moveto);
		term originalmoveto = rs.Return(im.tr, key);

		int selindex = holder.parent.getChilds().indexOf(holder);
		operator op =  null;
		term mid = new term();

//		Log.d(TAG, "moveto: "+moveto+" selindex: "+selindex+ " sel: "+sel+" equalsIndex: "+equalsIndex);
		
		if(!(moveto.operator instanceof plus)&&!(moveto.operator instanceof minus)){
			plus pl = new plus();
			int num = second.getChilds().indexOf(moveto);
			mid.parent = moveto.parent;
			mid.operator = pl;
			mid.getChilds().add(moveto);
			moveto.parent = mid;
			second.getChilds().set(num, mid);
			moveto = mid;
		
		}

		if(selindex > 0){ 
			
			op = (operator)holder.parent.getChilds().get(selindex-1);
			holder.parent.getChilds().remove(selindex-1);
			holder.parent.getChilds().remove(selindex-1);
			if((moveto.operator instanceof plus)||(moveto.operator instanceof minus)){
				if(op instanceof plus){op = new minus();}
				else if(op instanceof minus){op = new plus();}
				op.parent = moveto;
				holder.parent = moveto;
				moveto.getChilds().add(op);
				moveto.getChilds().add(holder);
			}
		}
		if(selindex == 0 ){

			if(holder.parent.getChilds().get(1) instanceof minus){
				holder.parent.getChilds().set(2, holder.parent.getChilds().get(2).toggleNegative());
			}

			if(holder.isNegative()){
				holder = holder.toggleNegative();
				holder.parent.getChilds().remove(0);
				holder.parent.getChilds().remove(0);

				plus pl = new plus();
				pl.parent = moveto;
				holder.parent = moveto;
				moveto.getChilds().add(pl);
				moveto.getChilds().add(holder);
			}
			else{
				minus mn = new minus();
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
		 
	

		image Ghost = new image(second.toString(), new point(im.bel.x,im.bel.y+(int)im.tr.container.height/2+100));
		Ghost = new image(Ghost.tr.toString(), Ghost.bel);
		return Ghost;


	}

	public void inTermFocus(term originalmoveto, term moveto, term holder, double xsel){

		int IntermIndex = Integer.MAX_VALUE;
		plus Plus = new plus();

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


				if(!(originalmoveto.getChilds().get(IntermIndex) instanceof operator)){
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




}
