package tree;

import java.util.ArrayList;

import container.walks.AssignScreenPositions;

import parse.path;
import representTerms.stringrect;
import tree.notsimple.Fraction;
import tree.notsimple.MultiplyFractions;
import tree.notsimple.NegativeTerm;
import tree.operators.divide;
import tree.operators.minus;
import tree.operators.negative;
import tree.operators.operator;
import tree.operators.times;
import tree.simple.Number;
import tree.simple.simpleterm;
//import android.util.Log;
import display.rectangle;

public class term implements Cloneable{

	static final String TAG = "term";
	
	public term parent;
	public operator operator;
	protected ArrayList<term> childs = new ArrayList<term>();
	public rectangle container = new rectangle();
	public String todraw;
	public boolean hasparen;
	public boolean issimple;
	private boolean isnegative = false;
	public boolean isbottom = false;
	public String valuestring;
	public float scalefactor =1;
	public ArrayList<simpleterm> simples = new ArrayList<simpleterm>();
	ArrayList<int[]> parens;
	operator[] ops;
	int numofterms;
	public double font = 1;

	public stringrect ScreenPosition = new stringrect();

	@Override
	public String toString(){
		String st = "";
		if(this instanceof simpleterm){
			st+=this.todraw;
		} 
		
		else if(truenum(this)<0){
			st+=truenum(this);
		}
		else if(isNegative()){
			st+="-";
			st+=getAbsoluteValue().toString();
		}
		else{
			st+="(";
			for(int i = 0; i<this.getChilds().size(); i++){
				st+=getChilds().get(i).toString();
			}
			st+=")";
		}
		return st;
	}


	@Override
	public Object clone() throws CloneNotSupportedException {
		String st = this.toString();
		path pa = new path();
		term clone = pa.getTermFromString(st);
		clone.font = this.font;
		clone.container.bl.x = this.container.bl.x;
		clone.container.bl.y = this.container.bl.y;
		return clone;
	}
	@Deprecated
	public term toggleNegative(term tr){
		tr.toggleNegative();
		return tr;
	}

	public term MultiplyLikeTerms(term tr){
		term ans = null;
		term sel = this;

		if(sel.SimpleCompound() && tr.SimpleCompound()){

		}

		else if (sel.Fraction() || tr.Fraction()){
			MultiplyFractions mf = new MultiplyFractions();
			ans = mf.times(sel, tr);

		}
		else if (sel.NaturalNumber() && tr.NaturalNumber()){
			term n = new term();
			double a = truenum(sel);
			double b = truenum(tr);
			double c = a*b;
			if(c>=0){
				n = new Number(c);
			}
			else{
				n = new Number(-c);
				n = n.toggleNegative();
			}
			ans = n;
		}
		return ans;
	}

	public term AddLikeTerms(term tr){

		term ans = null;

		term sel = this;
		if(sel.SimpleCompound()  && tr.SimpleCompound()){
			//add like terms that have variables
		}
		else if(sel instanceof Fraction || tr instanceof Fraction){


			if(sel instanceof Fraction &&(tr instanceof Fraction
					|| tr.NaturalNumber())){
				ans = ((Fraction)sel).add(tr);
			}
			else if(sel.NaturalNumber() && (tr instanceof Fraction
					|| tr.NaturalNumber())) {
				ans = ((Fraction)tr).add(sel);
			}
		}

		else if(sel.NaturalNumber() && tr.NaturalNumber()){
			term n = new term();
			double a = truenum(sel);
			double b = truenum(tr);
			double c = a+b;
			if(c>=0){
				n = new Number(c);
			}
			else{
				n = new Number(-c);
				n = n.toggleNegative();
			}
			ans = n;
		}


		return ans;
	}

	public double truenum(term tr){
		//this should only work for natural numbers
		//it should give the value of the number in the equation - considering a +/- in front of it
	//	Log.d(TAG, "Truenum: "+tr.getClass().getName().toString());
		if(!tr.NaturalNumber()){return 0;}
		double x = 0;
		if(!tr.isNegative()){
			if(tr.parent.getChilds().indexOf(tr) == 0){
				if(tr instanceof Number){
					x = ((Number)tr).value;
				}
				else if(tr.isNegative()){
					x = -(((Number)tr.getChilds().get(2)).value);
				}
			}
			else{
				int index = tr.parent.getChilds().indexOf(tr);
				x = ((Number)tr).value;
				term kid = tr.parent.getChilds().get(index-1);
				if(kid instanceof minus){
					x *= -1;
				}
			}
		}
		else{
			if(tr.getChilds().get(2) instanceof Number){
				x = -((Number)tr.getChilds().get(2)).value;
			}
			if(tr.parent.getChilds().indexOf(tr)!=0){
				int index = tr.parent.getChilds().indexOf(tr);
				term kid = tr.parent.getChilds().get(index-1);
				if(kid instanceof minus){
					x *= -1;
				}
			}
		}


		return x;
	}


	public boolean SimpleCompound(){
		term tr = this;
		boolean simp = false;
		if((tr.operator !=null)&&(tr.operator instanceof times)&&(tr.getChilds().size()>2)&&!(tr.RationalNumber())){ 
			boolean insidessimp = true;
			for(int i =0; i<tr.getChilds().size(); i++){
				if(!tr.getChilds().get(i).issimple){
					insidessimp = false;
				}
			}
			if(insidessimp){simp = true;}
		}

		return simp;
	}

	public boolean NaturalNumber(){
		term tr = this;
		boolean natural = false;
	//	Log.d(TAG, "this is: "+tr.getClass().getName().toString());
		try {
			if(tr instanceof Number){natural = true;}

			else if(tr.getChilds().size() == 2 && tr.getChilds().get(0) instanceof negative
					&& tr.getChilds().get(1) instanceof Number && tr.isNegative()){
				natural = true;
			}
		} catch (Exception e) {}

		return natural;

	}

	public boolean RationalNumber(){
		term tr = this;
		boolean rational = false;
		if(tr.NaturalNumber()){
			rational = true;
		}
		else if (tr.operator instanceof divide && tr.getChilds().size() == 3 &&
				tr.getChilds().get(0).NaturalNumber() && tr.getChilds().get(2).NaturalNumber()){
			rational = true;
		}
		return rational;
	}

	public boolean Fraction(){
		term tr = this;
		boolean fraction = false;
		if (tr.operator instanceof divide && tr.getChilds().size() == 3 &&
				tr.getChilds().get(0).NaturalNumber() && tr.getChilds().get(2).NaturalNumber()){
			fraction = true;
		}
		return fraction;
	}

	public term toggleNegative(){
		term mid;

		if(!this.isNegative()){
			NegativeTerm nt = new NegativeTerm(this);
			mid = nt;
		}
		else{
			mid = getAbsoluteValue();
		}

		return mid;
	}

	public term getAbsoluteValue(){
		term mid;
		
		if(isNegative()){
			if(this.getChilds().size()==2){
				mid = this.getChilds().get(1);
			}
			else if(this.getChilds().size()==3){
				mid = this.getChilds().get(2);
			}
			else{
				mid = null;
			}
		}
		else{
			mid = this;
		}
		return mid;
	}
	
	public void setChilds(ArrayList<term> childs) {
		this.childs = childs;
	}

	public ArrayList<term> getChilds() {
		return childs;
	}

	public void setNegative(boolean isnegative) {
		this.isnegative = isnegative;
	}

	public boolean isNegative() {
		boolean ans = false;
		if(this.operator != null){
			if(this.operator instanceof negative){
				ans= true;
			}

		}
		if(this instanceof NegativeTerm){
			ans = true;
		}
		return ans;
	}

	
	public void setScreenPositions(ArrayList<stringrect> screenPositions){
		AssignScreenPositions asp = new AssignScreenPositions(this, screenPositions);
	}

}
