package tree.simple;

import tree.Term;
import display.rectangle;

public class Constant extends Number{

	public Constant(double a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public static String[] CONSTANTS = {"pi","e"};

	public static double[] values = {Math.PI, Math.E};

	public int charpos;

	@Override
	public String toString(){
		return toDraw;
	}

	public Constant clone(){
		//	constants clone = (constants)super.clone();
		Constant c = new Constant(this.value);
		c.value = this.value;
		c.charpos = this.charpos;
		c.font = this.font;
		c.toDraw = this.toDraw;
		c.container.bl.x = this.container.bl.x;
		c.container.bl.y = this.container.bl.y;
		return c;
	}


	public rectangle giverect(Term tr){
		rectangle a = new rectangle();
		if(value == Math.E)
		{
			a.height = 1; 
			a.width = 1;
			tr.toDraw = "e";
			tr.container = a;}
		if(value == Math.PI)
		{
			a.height = 1; 
			a.width = 2;
			tr.toDraw = "pi";
			tr.container = a;}
		return a;
	}

	public boolean isoperator(){
		boolean a = false;
		return a;
	}
}
