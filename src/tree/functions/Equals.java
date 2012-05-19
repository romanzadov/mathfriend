package tree.functions;

import display.Rectangles;
import representTerms.Image;
import tree.compound.CompoundTerm;

public class Equals extends Function {

	String name = "=";

	public Equals(){
		inputs=2;
		invertible =false;
		commutative=true;
		distributive=false;
		associative=true;
	}

    @Override
    public CompoundTerm simpleOperation(CompoundTerm term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString(){
        return "=";
    }

    public Rectangles giverect(CompoundTerm tr){
		Rectangles a = new Rectangles();
		if(tr.getChildren().size() == 0){
			Rectangles cont = tr.getContainer();
		//	tr.toDraw = "=";
			a = justequal(cont);
		}
		else{
			//check to see that everything is rectangled
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i).getContainer() == null){
					System.out.println("error: equal called when not all terms are rectangled");
				}
			}
			//find top height
			float ysofar = 0;
			float xsofar = 0;
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i).getContainer().height>ysofar){
					ysofar = tr.getChildren().get(i).getContainer().height;
				}
			}

			//allign
			for(int i =0; i<tr.getChildren().size(); i++){
				Rectangles cont = tr.getChildren().get(i).getContainer();
				cont.bl.x = xsofar;
				xsofar +=cont.width;
				cont.bl.y = ysofar/2-cont.height/2;

			}

			a.width =  xsofar;
			a.height = ysofar;

		}
		return a;
	}

	public Rectangles justequal(Rectangles cont){
		Rectangles a = new Rectangles();
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

	public Image inTermMoves(Image im, CompoundTerm sel, int IntermIndex){

		int selindex = sel.getParent().getChildren().indexOf(sel);
		Image Ghost = im;
		Ghost = MoveFocus(im, sel, selindex, IntermIndex);
		
		return Ghost;

	}

	public Image MoveFocus(Image im, CompoundTerm sel, int selindex, int IntermIndex){
	/*	Image Ghost = new Image();
		CompoundTerm second = new CompoundTerm();
		try {
			second = (CompoundTerm) im.tr.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

//		CompoundTerm secondsel = second.getChildren().get(selindex);
//		CompoundTerm moveto = second.getChildren().get(IntermIndex);
/*
		if((moveto.getFunction() instanceof Plus)||(moveto.getFunction() instanceof Minus)){
			if(!(secondsel.getFunction() instanceof Plus)&&!(secondsel.getFunction() instanceof Minus)){
				
				Number zero = new Number(0);
				zero.setParent(secondsel.getParent());


				if (secondsel.isNegative()){
					secondsel = secondsel.toggleNegative();
					Plus pl = new Plus();
					moveto.getChildren().add(pl);
					moveto.getChildren().add(secondsel);
					pl.setParent(moveto);
					secondsel.setParent(moveto);
				}
				else{
		*//*			Minus mn = new Minus();
					moveto.getChildren().add(mn);
					moveto.getChildren().add(secondsel);
					mn.setParent(moveto);
					secondsel.setParent(moveto);*//*
				}
				zero.getParent().getChildren().set(selindex, zero);
				
			}
		}

		else if(!(secondsel.getFunction() instanceof Plus)&&!(secondsel.getFunction() instanceof Minus)
				&&!(moveto.getFunction() instanceof Plus)&&!(moveto.getFunction() instanceof Minus)
				&&!(selindex == IntermIndex)){
		
			*//*if(secondsel.isNegative()){
				secondsel = secondsel.toggleNegative();
				Plus pl = new Plus();
				Minus mn = new Minus();
				Number zero = new Number(0);
				Term mid = new Term();
				pl.setParent(mid);
				mn.setParent(mid);
				mid.setParent(secondsel.getParent());
				mid.setFunction(pl);
				zero.setParent(secondsel.getParent());
				secondsel.setParent(moveto.setParent(mid));
				mid.getParent().getChildren().set(selindex, zero);
				mid.getChildren().add(secondsel);
				mid.getChildren().add(pl);
				mid.getChildren().add(moveto);
				
				mid.getParent().getChildren().set(IntermIndex, mid);
				
			}
			else{
				Plus pl = new Plus();
				Minus mn = new Minus();
				Number zero = new Number(0);
				Term mid = new Term();
				pl.setParent(mid);
				mn.setParent(mid);
				mid.setParent(secondsel.getParent());
				mid.setFunction(pl);
				zero.setParent(secondsel.getParent());
				secondsel.setParent(moveto.setParent(mid));
				mid.getParent().getChildren().set(selindex, zero);
				
				mid.getChildren().add(moveto);
				mid.getChildren().add(mn);
				mid.getChildren().add(secondsel);
				
				mid.getParent().getChildren().set(IntermIndex, mid);
				
			}*//*
		}

		else{

			second.getChildren().set(IntermIndex, secondsel);
			second.getChildren().set(selindex, moveto);
		}*/
	//	RelativeContainer dn = new RelativeContainer();
	//	dn.drawelement(second);
	//	ColorText CT = new ColorText(secondsel, /.red);
	/*	Ghost = new Image(second.toString(), new point(im.bel.x,im.bel.y+(int) im.tr.getContainer().height/2+100));



		return Ghost;
*/
        return null;
	}
//	@Override
	public Image overEqualsMoves(Image im, CompoundTerm sel, int IntermIndex,
			double xsel) {
		// TODO Auto-generated method stub
		return null;
	}

}
