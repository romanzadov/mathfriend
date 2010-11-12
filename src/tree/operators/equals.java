package tree.operators;

import move.operators.plusmove;

import container.RelativeContainer;

import display.point;
import display.rectangle;
import representTerms.Image;
import tree.*;
import tree.simple.Number;

public class equals extends operator{

	String name = "=";

	public equals(){
		inputs=2;
		invertable=false;
		commutative=true;
		distributive=false;
		associative=true;
		orderofoperation=1000;
		lmult = false;
		rmult = false;
		todraw = "=";
	}
	public rectangle giverect(term tr){
		rectangle a = new rectangle();
		if(tr.getChilds().size() == 0){
			rectangle cont = tr.container;
			tr.todraw = "=";
			a = justequal(cont);
		}
		else{
			//check to see that everything is rectangled
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(i).container == null){
					System.out.println("error: equal called when not all terms are rectangled");
				}
			}
			//find top height
			float ysofar = 0;
			float xsofar = 0;
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(i).container.height>ysofar){
					ysofar = tr.getChilds().get(i).container.height;
				}
			}

			//allign
			for(int i =0; i<tr.getChilds().size(); i++){
				rectangle cont = tr.getChilds().get(i).container;
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

	public Image inTermMoves(Image im, term sel, int IntermIndex){

		int selindex = sel.parent.getChilds().indexOf(sel);
		Image Ghost = im;
		Ghost = MoveFocus(im, sel, selindex, IntermIndex);
		
		return Ghost;

	}

	public Image MoveFocus(Image im, term sel, int selindex, int IntermIndex){
		Image Ghost = new Image();
		term second = new term();
		try {
			second = (term) im.tr.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		term secondsel = second.getChilds().get(selindex);
		term moveto = second.getChilds().get(IntermIndex);

		if((moveto.operator instanceof plus)||(moveto.operator instanceof minus)){
			if(!(secondsel.operator instanceof plus)&&!(secondsel.operator instanceof minus)){
				
				Number zero = new Number(0);
				zero.parent = secondsel.parent;


				if (secondsel.isNegative()){
					secondsel = secondsel.toggleNegative();
					plus pl = new plus();
					moveto.getChilds().add(pl);
					moveto.getChilds().add(secondsel);
					pl.parent = moveto;
					secondsel.parent = moveto;
				}
				else{
					minus mn = new minus();
					moveto.getChilds().add(mn);
					moveto.getChilds().add(secondsel);
					mn.parent = moveto;
					secondsel.parent = moveto;
				}
				zero.parent.getChilds().set(selindex, zero);
				
			}
		}

		else if(!(secondsel.operator instanceof plus)&&!(secondsel.operator instanceof minus)
				&&!(moveto.operator instanceof plus)&&!(moveto.operator instanceof minus)
				&&!(selindex == IntermIndex)){
		
			if(secondsel.isNegative()){
				secondsel = secondsel.toggleNegative();
				plus pl = new plus();
				minus mn = new minus();
				Number zero = new Number(0);
				term mid = new term();
				pl.parent = mid;
				mn.parent = mid;
				mid.parent = secondsel.parent;
				mid.operator = pl;
				zero.parent = secondsel.parent;
				secondsel.parent = moveto.parent = mid;
				mid.parent.getChilds().set(selindex, zero);
				mid.getChilds().add(secondsel);
				mid.getChilds().add(pl);
				mid.getChilds().add(moveto);
				
				mid.parent.getChilds().set(IntermIndex, mid);
				
			}
			else{
				plus pl = new plus();
				minus mn = new minus();
				Number zero = new Number(0);
				term mid = new term();
				pl.parent = mid;
				mn.parent = mid;
				mid.parent = secondsel.parent;
				mid.operator = pl;
				zero.parent = secondsel.parent;
				secondsel.parent = moveto.parent = mid;
				mid.parent.getChilds().set(selindex, zero);
				
				mid.getChilds().add(moveto);
				mid.getChilds().add(mn);
				mid.getChilds().add(secondsel);
				
				mid.parent.getChilds().set(IntermIndex, mid);
				
			}
		}

		else{

			second.getChilds().set(IntermIndex, secondsel);
			second.getChilds().set(selindex, moveto);			
		}
	//	RelativeContainer dn = new RelativeContainer();
	//	dn.drawelement(second);
	//	ColorText CT = new ColorText(secondsel, /.red);
		Ghost = new Image(second.toString(), new point(im.bel.x,im.bel.y+(int)im.tr.container.height/2+100));



		return Ghost;

	}
	@Override
	public Image overEqualsMoves(Image im, term sel, int IntermIndex,
			double xsel) {
		// TODO Auto-generated method stub
		return null;
	}

}