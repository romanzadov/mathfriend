package move.operators;

//import android.util.Log;


public class plusmove {/*

	static final String TAG = "plusmove";

	public Image overEquals(Image im, Term sel, int equalsIndex, double xsel){
		//path pa = new path();
		//term second = pa.pathway(im.ft.originalstring);
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
		Term holder = rs.Return(second, key);

//		ColorText ct = new ColorText(holder, Color.red);

		Term moveto = holder.parent.parent.getChilds().get(equalsIndex);
		key = fs.FindSelected(second, moveto);
		Term originalmoveto = rs.Return(im.tr, key);

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

	public void inTermFocus(Term originalmoveto, Term moveto, Term holder, double xsel){

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


*/

}

