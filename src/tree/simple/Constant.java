package tree.simple;

import representTerms.stringrect;
import tree.Term;
import display.rectangle;

public class Constant extends Number{

	public static String[] CONSTANTS = {"pi","e"};
	
	public static double[] values = {Math.PI, Math.E};
	
	public double value;
	
	public int charpos;
	
	@Override
	public String toString(){
		return todraw;
	}
	
	public Constant clone(){
	//	constants clone = (constants)super.clone();
		Constant c = new Constant();
		c.value = this.value;
		c.charpos = this.charpos;
		c.font = this.font;
		c.todraw = this.todraw;
		c.container.bl.x = this.container.bl.x;
		c.container.bl.y = this.container.bl.y;
		return c;
	}
	
	public Constant(){
	rmult = true;
	lmult = true;
	}
	
	public rectangle giverect(Term tr){
		rectangle a = new rectangle();
		if(value == Math.E)
		{
		a.height = 1; 
		a.width = 1;
		tr.todraw = "e";
		tr.container = a;}
		if(value == Math.PI)
		{
		a.height = 1; 
		a.width = 2;
		tr.todraw = "pi";
		tr.container = a;}
		return a;
	}
	
	public boolean isoperator(){
		boolean a = false;
		return a;
	}
}