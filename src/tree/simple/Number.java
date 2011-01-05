package tree.simple;

import parse.path;
import tree.Term;
import display.rectangle;

public class Number extends simpleterm{
	
	public double value ;
	public int charpos;
	
	
	@Override
	public String toString(){
		return ""+value;
	}
	
	@Override
	public Number clone(){
		
		Number na = new Number(this.value);
		na.font = this.font;
		na.container.bl.x = this.container.bl.x;
		na.container.bl.y = this.container.bl.y;
		return na;
	}
	
	public  Number(){
		rmult = true;
		lmult = true;
	}
	
	public Number(double a){
		rmult = true;
		lmult = true;
		value = a;
		todraw = "0";
	}
	
	public rectangle giverect(Term tr){
		rectangle a = new rectangle();
			
			if(isNegative())
			{//if the term is a "negative"
				tr.todraw = "-";
				a.height = 1;
				a.width = (float) .5;
				tr.container = a;
			}
			else if(value%1==0)
			{int b = (int)value;
			 tr.todraw = ""+b;
			 a.height = 1;
			 a.width = tr.todraw.length();
			 tr.container = a;}
			else if((value*1000)%1==0)
			{tr.todraw = ""+value;
			 a.height = 1;
			 a.width = tr.todraw.length();
			 tr.container = a;}
			
			else
			{   double trunkated = (int)(value*1000);
				trunkated = 0.0+ trunkated/1000;
				tr.todraw = ""+trunkated+"...";
				a.height = 1;
				a.width = tr.todraw.length();
				tr.container = a;
			}
		return a;
	}
	
	public void setvalue(double a){
		value = a;
	}
	
	public double getValue(){
		return value;
	}
	
	public boolean isoperator(){
		boolean a = false;
		return a;
	}

	public boolean equals(Object a)
	{
		boolean ans = false;
		if(a.equals(value)){ans=true;}
		return ans;
	}
	
	
	

}
