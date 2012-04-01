package tree.simple;

import tree.Term;
import display.rectangle;

public class Number extends SimpleTerm {
	
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
		na.getContainer().bl.x = this.getContainer().bl.x;
		na.getContainer().bl.y = this.getContainer().bl.y;
		return na;
	}
	
	public Number(double a){
		rmult = true;
		lmult = true;
		value = a;
		toDraw = "0";
		if(a<0){
			setNegative(true);
		}
	}
	
	public rectangle giverect(Term tr){
		rectangle a = new rectangle();
			
			if(isNegative())
			{//if the term is a "negative"
				tr.toDraw = "-";
				a.height = 1;
				a.width = (float) .5;
				tr.setContainer(a);
			}
			else if(value%1==0)
			{int b = (int)value;
			 tr.toDraw = ""+b;
			 a.height = 1;
			 a.width = tr.toDraw.length();
			 tr.setContainer(a);}
			else if((value*1000)%1==0)
			{tr.toDraw = ""+value;
			 a.height = 1;
			 a.width = tr.toDraw.length();
			 tr.setContainer(a);}
			
			else
			{   double trunkated = (int)(value*1000);
				trunkated = 0.0+ trunkated/1000;
				tr.toDraw = ""+trunkated+"...";
				a.height = 1;
				a.width = tr.toDraw.length();
				tr.setContainer(a);
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
