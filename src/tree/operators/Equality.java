package tree.operators;

import display.point;
import display.rectangle;
import representTerms.Image;
import tree.*;
import tree.simple.Number;

public class Equality extends Operator{

	String name = "=";

	public Equality(){
		inputs=2;
		invertable=false;
		commutative=true;
		distributive=false;
		associative=true;
		orderofoperation=1000;
		lmult = false;
		rmult = false;
		toDraw = "=";
	}

    @Override
    public Term simpleOperation(Term term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public rectangle giverect(Term tr){
		rectangle a = new rectangle();
		if(tr.getChildren().size() == 0){
			rectangle cont = tr.container;
			tr.toDraw = "=";
			a = justequal(cont);
		}
		else{
			//check to see that everything is rectangled
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i).container == null){
					System.out.println("error: equal called when not all terms are rectangled");
				}
			}
			//find top height
			float ysofar = 0;
			float xsofar = 0;
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i).container.height>ysofar){
					ysofar = tr.getChildren().get(i).container.height;
				}
			}

			//allign
			for(int i =0; i<tr.getChildren().size(); i++){
				rectangle cont = tr.getChildren().get(i).container;
				cont.bl.x = xsofar;
				xsofar +=cont.width;
				cont.bl.y = ysofar/2-cont.height/2;

			}

			a.width =  xsofar;
			a.height = ysofar;

		}
		return a;
	}

	public rectangle justequal(rectangle cont){
		rectangle a = new rectangle();
		a.height = 1;
		a.width = 1;
		cont = a;
		return cont;
	}
	public boolean function(double a, double b)
	{
		boolean c = a==b;
		return c;
	}

	public Image inTermMoves(Image im, Term sel, int IntermIndex){

		int selindex = sel.parent.getChildren().indexOf(sel);
		Image Ghost = im;
		Ghost = MoveFocus(im, sel, selindex, IntermIndex);
		
		return Ghost;

	}

	public Image MoveFocus(Image im, Term sel, int selindex, int IntermIndex){
		Image Ghost = new Image();
		Term second = new Term();
		try {
			second = (Term) im.tr.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Term secondsel = second.getChildren().get(selindex);
		Term moveto = second.getChildren().get(IntermIndex);

		if((moveto.operator instanceof Plus)||(moveto.operator instanceof Minus)){
			if(!(secondsel.operator instanceof Plus)&&!(secondsel.operator instanceof Minus)){
				
				Number zero = new Number(0);
				zero.parent = secondsel.parent;


				if (secondsel.isNegative()){
					secondsel = secondsel.toggleNegative();
					Plus pl = new Plus();
					moveto.getChildren().add(pl);
					moveto.getChildren().add(secondsel);
					pl.parent = moveto;
					secondsel.parent = moveto;
				}
				else{
					Minus mn = new Minus();
					moveto.getChildren().add(mn);
					moveto.getChildren().add(secondsel);
					mn.parent = moveto;
					secondsel.parent = moveto;
				}
				zero.parent.getChildren().set(selindex, zero);
				
			}
		}

		else if(!(secondsel.operator instanceof Plus)&&!(secondsel.operator instanceof Minus)
				&&!(moveto.operator instanceof Plus)&&!(moveto.operator instanceof Minus)
				&&!(selindex == IntermIndex)){
		
			if(secondsel.isNegative()){
				secondsel = secondsel.toggleNegative();
				Plus pl = new Plus();
				Minus mn = new Minus();
				Number zero = new Number(0);
				Term mid = new Term();
				pl.parent = mid;
				mn.parent = mid;
				mid.parent = secondsel.parent;
				mid.operator = pl;
				zero.parent = secondsel.parent;
				secondsel.parent = moveto.parent = mid;
				mid.parent.getChildren().set(selindex, zero);
				mid.getChildren().add(secondsel);
				mid.getChildren().add(pl);
				mid.getChildren().add(moveto);
				
				mid.parent.getChildren().set(IntermIndex, mid);
				
			}
			else{
				Plus pl = new Plus();
				Minus mn = new Minus();
				Number zero = new Number(0);
				Term mid = new Term();
				pl.parent = mid;
				mn.parent = mid;
				mid.parent = secondsel.parent;
				mid.operator = pl;
				zero.parent = secondsel.parent;
				secondsel.parent = moveto.parent = mid;
				mid.parent.getChildren().set(selindex, zero);
				
				mid.getChildren().add(moveto);
				mid.getChildren().add(mn);
				mid.getChildren().add(secondsel);
				
				mid.parent.getChildren().set(IntermIndex, mid);
				
			}
		}

		else{

			second.getChildren().set(IntermIndex, secondsel);
			second.getChildren().set(selindex, moveto);
		}
	//	RelativeContainer dn = new RelativeContainer();
	//	dn.drawelement(second);
	//	ColorText CT = new ColorText(secondsel, /.red);
		Ghost = new Image(second.toString(), new point(im.bel.x,im.bel.y+(int)im.tr.container.height/2+100));



		return Ghost;

	}
	@Override
	public Image overEqualsMoves(Image im, Term sel, int IntermIndex,
			double xsel) {
		// TODO Auto-generated method stub
		return null;
	}

}
