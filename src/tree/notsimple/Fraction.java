package tree.notsimple;
import tree.Term;
import tree.operators.divide;
import tree.operators.Minus;
import tree.simple.Number;
import display.rectangle;

public class Fraction extends NotSimple{

	
	Term top;
	Term bottom;
	
	public Fraction(){
		divide dv = new divide();
		this.operator = dv;}
	
	public Fraction(Term tr){
		divide dv = new divide();
		this.operator = dv;
		general(tr.getChilds().get(0), tr.getChilds().get(2));
		hasparen = tr.hasparen;
	}	
	
	public Fraction(Term t, Term b){
		divide dv = new divide();
		this.operator = dv;
		general(t,  b);
	}
	
	public Fraction FractionOverOne(Term tr){
		
		Fraction f = new Fraction();
		
		Number one = new Number(1);
		divide dv = new divide();
		
		Term second =  new Term();
		try {
			 second = (Term)tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		f.top = tr;
		f.bottom = one;
		
		f.parent = tr.parent;
		
		second.parent = f;
		dv.parent = f;
		one.parent = f;
		
		f.getChilds().add(second);
		f.getChilds().add(dv);
		f.getChilds().add(one);
		
		return f;
	}
	
	public void general(Term t, Term b){
		divide dv = new divide();
		divide op = new divide();
		this.operator = op;	
		try {
			top = (Term)t.clone();
			bottom = (Term)b.clone();
		} catch (CloneNotSupportedException e) {}
		
		this.getChilds().add(top);
		this.getChilds().add(dv); 	
		this.getChilds().add(bottom);
		
		top.parent = this;
		dv.parent = this;
		bottom.parent = this;
	}
	
	public boolean isNaturalFraction(Term tr){
		boolean ans = false;
		if(((Fraction)tr).isNegative(tr)){
			if(tr.getChilds().get(0).NaturalNumber() && tr.getChilds().get(2).NaturalNumber()){
				ans = true;
			}
		}
		
		return ans;
	}
	
	public boolean isNegative(Term tr){
		boolean ans = false;
		
		try {
			if(!tr.issimple && tr.getChilds().size()==3 && tr.getChilds().get(1) instanceof divide){
				ans = true;
			}
		} catch (Exception e) {}
		return ans;
	}
	
	public Fraction multiply(Term tr){
		return null;
	}
	
	public Fraction add(Term tr){
		Fraction f = new Fraction();
		
		
		if(tr.NaturalNumber()){
			
			int place = tr.parent.getChilds().indexOf(tr);
			Fraction fr = FractionOverOne(tr);
			tr.parent.getChilds().set(place, fr);
			tr = fr;
			
		}
		// the fractions will be a/b and c/d
		
		double a = topnum(this);
		double b = truenum(this.getChilds().get(2));
		double c = topnum(tr);
		double d = truenum(tr.getChilds().get(2));
		
		double lcd = LCD(b, d);
		
		f = new Fraction(new Number(lcd/b*a+lcd/d*c), new Number(lcd));
		
		return f;
	}
	
	public rectangle giverect(Term tr){
	
		rectangle a = new rectangle();
		float xmax= 0;
		float ymax = 0;
		for(int i = 0; i<tr.getChilds().size(); i++){
			rectangle b = tr.getChilds().get(i).container;
			if(b.width>xmax){xmax = b.width;}
			ymax += b.height;
		}
		a.height = ymax;
		a.width = xmax;
		tr.container = a;
		return a;
	}
	
	public double topnum(Term tr){
		//will give the true number of the top of the fraction
		//noticing the +/- in front or negative
		
		int negcount = 0;
		
		if(tr.isNegative()){negcount++;}
		if(tr.getChilds().get(0).isNegative()){negcount++;}
		if(tr.parent != null){
			int place = tr.parent.getChilds().indexOf(tr);
			if(place>0){
				if(tr.parent.getChilds().get(place-1) instanceof Minus){negcount++;}
			}
		}
		
		double ans;
		double val = 0;
		if(tr.getChilds().get(0) instanceof Number){
			 val = ((Number)tr.getChilds().get(0)).value;
			
		}
		else if(tr.getChilds().get(0).getChilds().get(2) instanceof Number){
			val = ((Number)tr.getChilds().get(0).getChilds().get(2)).value;
		}
		ans = Math.pow(-1, negcount)*val;
		
		return ans;
	}

	public double LCD(double A, double B){
		
		int a = (int)A;
		int b = (int)B;
		
		int c = Math.max(a, b);
		int d = Math.min(a, b);
		
		int LCD = c;
		
		while(LCD%d != 0){
			LCD += c;
		}
		
		return LCD;
	}
}
