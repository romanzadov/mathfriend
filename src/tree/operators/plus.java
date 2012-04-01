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
		setValueString("+");
	}

    @Override
    public Term simpleOperation(Term term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //draws the rectangle that goes around terms with a plus operator
	public rectangle giverect(Term tr){
		rectangle a = new rectangle();

        if(tr.getChildren().size()>=1){
			//check rectangled and get max height
			float maxheight = 0;
			for(int i =0; i<tr.getChildren().size();i++){
				if(tr.getChildren().get(i).getContainer() ==null){
					System.out.println("error: plus called on non-rectangled");
				}
				if(tr.getChildren().get(i).getContainer().height>maxheight){
					maxheight = tr.getChildren().get(i).getContainer().height;
				}
			}
			float xsofar = 0;
			for(int i =0; i<tr.getChildren().size();i++){
				rectangle left = tr.getChildren().get(i).getContainer();
				left.bl.y = maxheight/2-left.height/2;
				left.bl.x = xsofar;
				xsofar = left.bl.x+left.width;
			}
			a.height = maxheight;
			a.width = xsofar;
		}


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
		if(!secondsel.getParent().isHasParentheses()){
			changeterm(secondsel, IntermIndex);
		}
		else{
			
		}

	//	RelativeContainer dn = new RelativeContainer();
	//	dn.drawelement(second, (int)im.tr.font);
		Image Ghost = new Image(second.toString(), new point(im.bel.x,im.bel.y+(int) im.tr.getContainer().height/2+100));

		return Ghost;
	}

	public Image overEqualsMoves(Image im, Term sel, int IntermIndex, double xsel){
		
		PlusMove pm = new PlusMove();
		Image Ghost = new Image();
		if(!(sel.getParent().getParent().getChildren().get(IntermIndex) instanceof Operator)){
		//	Ghost = pm.overEquals(im, sel, IntermIndex, xsel);
		}
		return Ghost;
	}


	public void changeterm( Term sel, int IntermIndex){
	
		int selindex = sel.getParent().getChildren().indexOf(sel);


		Term holder = sel;
		Term moveto = sel.getParent().getChildren().get(IntermIndex);
	//	ColorText CT = new ColorText(holder, Color.red);
		



		if(selindex != IntermIndex){
			if(sel.getParent().isHasParentheses()){IntermIndex++; selindex++;}  // in parens still doesn't work

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

		}

	}
}
