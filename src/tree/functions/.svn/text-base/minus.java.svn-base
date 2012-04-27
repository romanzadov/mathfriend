package tree.operators;
import move.operators.plusmove;
import display.rectangle;
import representTerms.image;
import tree.*;
public class minus extends operator{
	
	
	public minus(){
		inputs =2;
	    invertable=true;
		commutative=true;
		distributive=true;
		associative=true;
		identity=0;
		orderofoperation=5;
		lmult = false;
		rmult = false;
		todraw = "-";
		valuestring = "-";
	}
	public rectangle giverect(term tr){
		rectangle a = new rectangle();
		plus p = new plus();
		a = p.giverect(tr);
		return a;
	}
	
	public image inTermMoves(image im, term sel, int IntermIndex){
		plus p = new plus();
		im = p.inTermMoves(im, sel, IntermIndex);
		return im;
	}
	public image overEqualsMoves(image im, term sel, int IntermIndex, double xsel){
		plus p = new plus();
		im = p.overEqualsMoves(im, sel, IntermIndex, xsel);
		return im;
	}
	
	
	public double function(double a, double b)
	{
		double c = a-b;
		return c;
	}
}
