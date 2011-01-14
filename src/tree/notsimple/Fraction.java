package tree.notsimple;
import java.util.ArrayList;

import parse.path;

import representTerms.Image;
import tree.Term;
import tree.operators.Divide;
import tree.operators.Minus;
import tree.simple.Number;
import display.rectangle;

public class Fraction extends NotSimple{

	Term top;
	Divide divide = new Divide();
	Term bottom;

	public Term getTop(){
		return getChilds().get(0);
	}

	public Term getBottom(){
		return getChilds().get(2);
	}

	@Override
	public ArrayList<Term> getChilds(){
		ArrayList<Term> kids = new ArrayList<Term>();
		kids.add(top);
		kids.add(divide);
		kids.add(bottom);
		return kids;
	}
	
	public void setChilds(ArrayList<Term> childs) {
		if(childs.size() != 3){System.out.println("wrong number of childs for fraction");}
		top = childs.get(0);
		bottom = childs.get(2);
	}

	public Fraction(Term t, Term b){

		Divide dv = new Divide();
		this.operator = dv;

			top = t;
			bottom = b;

		this.top.parent = this;
		this.divide.parent = this;
		this.bottom.parent = this;
	}






	public Fraction() {
		// TODO Auto-generated constructor stub
	}

	public boolean isNaturalFraction(){
		boolean ans = false;

		if(this.getTop().isDecimal() && this.getBottom().isDecimal()){ans = true;}

		return ans;
	}


	public Fraction add(Term tr){


		if(tr.isInteger()){

			int place = tr.parent.getChilds().indexOf(tr);
			Fraction fr = fractionOverOne(tr);
			tr.parent.getChilds().set(place, fr);
			tr = fr;

		}
		// the fractions will be a/b and c/d

		double a = topnum(this);
		double b = getNumericValue(this.getChilds().get(2));
		double c = topnum(tr);
		double d = getNumericValue(tr.getChilds().get(2));

		double lcd = LCD(b, d);

		Fraction f = new Fraction(new Number(lcd/b*a+lcd/d*c), new Number(lcd));

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

	public static double LCD(double A, double B){

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

	public static Term productOfNaturalFractions(Term a, Term b){

		Term result = null;
		
		
		if(a instanceof Fraction && b instanceof Fraction){
			result = fractionTimesFraction((Fraction)a, (Fraction)b);
			return result;
		}
		else{
			result = fractionTimesNumber(a,b);
		}
		
		
		
		return result;

	}

	private static Term fractionTimesNumber(Term a, Term b){
		boolean onefraction = false;
		if(a.isFraction() || b.isFraction()){
			if(a.isDecimal() || b.isDecimal()){
				onefraction = true;
			}
		}
		if(!onefraction){return null;}

		Term fraction = null;
		Term number = null;
		if(a.isFraction()){fraction = a; number = b;}
		else{fraction = b; number = a;}

		String newTop = "";
		
		if(fraction == a ){
			newTop = fraction.getChilds().get(0).toString()+"*("+number.toString()+")";
		}
		else{
			newTop = number.toString()+"*("+fraction.getChilds().get(0).toString()+")";
		}
		
		String newBottom = fraction.getChilds().get(2).toString();

		String multipliedFraction = "("+newTop+")/("+newBottom+")";
		Image img = new Image(multipliedFraction, 2,2,2);	
		

		return img.tr;
	}

	private static Fraction fractionTimesFraction(Fraction a, Fraction b){

		//check to see that both terms are natural fractions
		boolean bothfractions = false;
	//	if(a.isNaturalFraction() && b.isNaturalFraction()){ bothfractions = true;}
	//	if(!bothfractions){return null;}

		//
		String newTop = a.getChilds().get(0).toString()+"*("+b.getChilds().get(0).toString()+")";
		String newBottom = a.getChilds().get(2).toString()+"*("+b.getChilds().get(2).toString()+")";

		path pa = new path();
		Term top = pa.getTermFromString(newTop);

		pa = new path();
		Term bottom = pa.getTermFromString(newBottom);

		
		Fraction result = new Fraction(top, bottom);


		return result;
	}

	@Override
	public boolean canConstruct(Term tr) {

		boolean fraction = false;

		if(tr.getChilds().size() == 3){
			if(tr.getChilds().get(1) instanceof Divide){
				fraction = true;
			}
		}

		return fraction;
	}

}
