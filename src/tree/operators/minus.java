package tree.operators;
import display.rectangle;
import representTerms.Image;
import tree.Term;

public class Minus extends Operator {
	
	
	public Minus(){
		inputs =2;
	    invertable=true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=0;
		orderofoperation=5;
		lmult = false;
		rmult = false;
		toDraw = "-";
		setValueString("-");
	}

    @Override
    public Term simpleOperation(Term term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public rectangle giverect(Term tr){
		rectangle a = new rectangle();
		Plus p = new Plus();
		a = p.giverect(tr);
		return a;
	}
	
	public Image inTermMoves(Image im, Term sel, int IntermIndex){
		Plus p = new Plus();
		im = p.inTermMoves(im, sel, IntermIndex);
		return im;
	}
	public Image overEqualsMoves(Image im, Term sel, int IntermIndex, double xsel){
		Plus p = new Plus();
		im = p.overEqualsMoves(im, sel, IntermIndex, xsel);
		return im;
	}
	
	
	public double function(double a, double b)
	{
		double c = a-b;
		return c;
	}
}
