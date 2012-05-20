package tree.functions;

import move.operators.plusmove;
import representTerms.Image;
import tree.compound.CompoundTerm;
import display.Rectangle;
//import android.util.Log;

public class Plus extends Function {

	public boolean invisible;
	
	public Plus(){
		inputs =2;
		invertible =true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=0;
		invisible = false;
	}

    @Override
    public String toString() {
        return "+";
    }

    @Override
    public CompoundTerm simpleOperation(CompoundTerm term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

	public double function(double a, double b)
	{
		double c = a + b;
		return c;
	}

	public Image inTermMoves(Image im, CompoundTerm sel, int IntermIndex){

		/*CompoundTerm second = new CompoundTerm();
		try {
			second = (CompoundTerm)im.tr.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FindSel fs = new FindSel();

		ArrayList<Integer> key = fs.FindSelected(im.tr, sel);
		ReturnSel rs = new ReturnSel();
		CompoundTerm secondsel = rs.Return(second, key);
		if(!secondsel.getParent().hasParentheses()){
			changeterm(secondsel, IntermIndex);
		}
		else{
			
		}

	//	RelativeContainer dn = new RelativeContainer();
	//	dn.drawelement(second, (int)im.tr.font);
		Image Ghost = new Image(second.toString(), new point(im.bel.x,im.bel.y+(int) im.tr.getContainer().height/2+100));

		return Ghost;*/
        return null;
	}

	public Image overEqualsMoves(Image im, CompoundTerm sel, int IntermIndex, double xsel){
		
		plusmove pm = new plusmove();
		Image Ghost = new Image();/*
		if(!(sel.getParent().getParent().getChildren().get(IntermIndex) instanceof Function)){
		//	Ghost = pm.overEquals(im, sel, IntermIndex, xsel);
		}*/
		return Ghost;
	}


	public void changeterm( CompoundTerm sel, int IntermIndex){/*
	
		int selindex = sel.getParent().getChildren().indexOf(sel);


		Term holder = sel;
		Term moveto = sel.getParent().getChildren().get(IntermIndex);
	//	ColorText CT = new ColorText(holder, Color.red);
		



		if(selindex != IntermIndex){
			if(sel.getParent().hasParentheses()){IntermIndex++; selindex++;}  // in parens still doesn't work

			if(selindex == 0 ){
				if(holder.isNegative()){
					holder = holder.toggleNegative();
					Minus mn = new Minus();
					mn.setParent(holder.getParent());
	//				mn.wordcolor = Color.red;
					holder.getParent().getChildren().add(IntermIndex+1, holder);
					holder.getParent().getChildren().add(IntermIndex+1, mn);
				}
				else{
					Plus pl = new Plus();
					pl.setParent(holder.getParent());
	//				pl.wordcolor = Color.red;
					holder.getParent().getChildren().add(IntermIndex+1, holder);
					holder.getParent().getChildren().add(IntermIndex+1, pl);
				}
				holder.getParent().getChildren().remove(0);
				Term op = holder.getParent().getChildren().get(0);
				if(op instanceof Plus){
					holder.getParent().getChildren().remove(0);
				}
				if(op instanceof Minus){
					holder.getParent().getChildren().remove(0);
					holder.getParent().getChildren().get(0).toggleNegative();
				}
			}
			else if(IntermIndex == 0){

				if(holder.getParent().getChildren().get(selindex-1) instanceof Minus){
					Term mid = holder.toggleNegative();
		//			Log.d(TAG, "mid: "+mid);
					mid.setParent(holder.getParent());
					holder = mid;
		//			Log.d(TAG, "holder: "+holder);
				}
		//		Log.d(TAG, "parent: "+holder.parent+" hold: "+holder+" selindex: "+selindex);
				
				holder.getParent().getChildren().remove(selindex-1);
				holder.getParent().getChildren().remove(selindex-1);

				if(moveto.isNegative()){
					moveto.toggleNegative();
					Minus mn = new Minus();
					mn.setParent(holder.getParent());
					holder.getParent().getChildren().add(0,mn);
				}
				else{
					Plus pl = new Plus();
					pl.setParent(holder.getParent());
					holder.getParent().getChildren().add(0, pl);
				}
				holder.getParent().getChildren().add(0, holder);


			}
			else{
				Term op = holder.getParent().getChildren().get(selindex-1);
				holder.getParent().getChildren().remove(selindex-1);
				holder.getParent().getChildren().remove(selindex-1);
				holder.getParent().getChildren().add(IntermIndex-1, holder);
				holder.getParent().getChildren().add(IntermIndex-1, op);
	//			op.wordcolor = Color.red;
			}

		}*/

	}
}
