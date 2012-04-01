package tree;

import java.util.ArrayList;

import container.walks.AssignScreenPositions;

import parse.path;
import representTerms.StringRectangle;
import tree.downwalk.TreeFunction;
import tree.notsimple.Fraction;
import tree.notsimple.MultiplyFractions;
import tree.notsimple.NegativeTerm;
import tree.operators.*;
import tree.simple.Number;
import tree.simple.Constant;
import tree.simple.SimpleTerm;
//import android.util.Log;
import display.rectangle;

public class Term implements Cloneable, TreeFunction{

	public Term parent;
	public Operator operator;
	protected ArrayList<Term> children = new ArrayList<Term>();
	public rectangle container = new rectangle();

	public boolean hasParentheses;
	protected boolean isNegative = false;
	public String valueString;
	public float scaleFactor =1;
	public ArrayList<SimpleTerm> simples = new ArrayList<SimpleTerm>();

	public StringRectangle ScreenPosition = new StringRectangle();


    public boolean isSimple() {
        if (this instanceof SimpleTerm) {
            return true;
        }
        return false;
    }

    public String getToDraw() {
        return this.toString();
    }

	@Override
	public String toString(){
		String st = "";
		if(this instanceof SimpleTerm){
			st+=this.valueString;
		} 

		else if(this.isInteger() && getNumericValue(this)<0){
			st+=getNumericValue(this);
		}
		else if(isNegative()){
			st+="-";
			st+=getAbsoluteValue().toString();
		}
		else{
			st+="(";
			for(int i = 0; i<this.getChildren().size(); i++){
				st+= getChildren().get(i).toString();
			}
			st+=")";
		}
		return st;
	}


	@Override
	public Object clone() throws CloneNotSupportedException {
		String st = this.toString();
		path pa = new path();
		Term clone = pa.getTermFromString(st);
		clone.container.bl.x = this.container.bl.x;
		clone.container.bl.y = this.container.bl.y;

		return clone;
	}
	
	
	@Deprecated
	public Term toggleNegative(Term tr){
		tr.toggleNegative();
		return tr;
	}

	public Term MultiplyLikeTerms(Term tr){
		Term ans = null;
		Term sel = this;

		if(sel.SimpleCompound() && tr.SimpleCompound()){

		}

		else if (sel.isSimpleFraction() || tr.isSimpleFraction()){
			MultiplyFractions mf = new MultiplyFractions();
			ans = mf.times(sel, tr);

		}
		else if (sel.isInteger() && tr.isInteger()){
			Term n = new Term();
			double a = getNumericValue(sel);
			double b = getNumericValue(tr);
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

	public Term AddLikeTerms(Term tr){

		Term ans = null;

		Term sel = this;
		if(sel.SimpleCompound()  && tr.SimpleCompound()){
			//add like terms that have variables
		}
		else if(sel instanceof Fraction || tr instanceof Fraction){


			if(sel instanceof Fraction &&(tr instanceof Fraction
					|| tr.isInteger())){
				ans = ((Fraction)sel).add(tr);
			}
			else if(sel.isInteger() && (tr instanceof Fraction
					|| tr.isInteger())) {
				ans = ((Fraction)tr).add(sel);
			}
		}

		else if(sel.isInteger() && tr.isInteger()){
			Term n = new Term();
			double a = getNumericValue(sel);
			double b = getNumericValue(tr);
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

	public Fraction getReciprical(){
		
		Term a = new Term();
		try {
			a = (Term)this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(a.isFraction()){
			Fraction input = (Fraction)a;
			Fraction output = new Fraction(input.getBottom(), input.getTop());
			return output;
		}
		else{
			Fraction input = fractionOverOne(a);
			Fraction output = new Fraction(input.getBottom(), input.getTop());
			return output;
		}
		
	}
	
	
	public static Fraction fractionOverOne(Term tr){
		
		//take a term and put it over one
		
		Number one = new Number(1);
		Fraction f = new Fraction(tr, one);
		return f;
		
	}
	
	public static double getNumericValue(Term tr){
		//it should give the value of the number in the equation - considering a +/- in front of it
		//	Log.d(TAG, "Truenum: "+tr.getClass().getName().toString());
		//return Double.Min if there's no answer
		double x = Double.MIN_VALUE;

		if(!tr.isRealNumber()){
			return Double.MIN_VALUE;
		}

		if(tr.isSimpleFraction()){
			return Double.MIN_VALUE;
		}



		if(tr.isSimple()){
			
			if(tr instanceof Number){

				x = ((Number)tr).value;
				int positionOfX = tr.parent.getChildren().indexOf(tr);
				
				if(positionOfX == 0){
					
					if(tr.isNegative()){
						x*= -1;
					}
					
					return x;
				}
				else{
					if(tr.parent.getChildren().get(positionOfX - 1) instanceof Minus){
						x*= -1;
					}
				}

			}
		}



		if(!tr.isNegative()){
			if( tr.parent.getChildren().indexOf(tr) == 0){
				if(tr instanceof Number){
					x = ((Number)tr).value;
				}
			}
			else{
				int index = tr.parent.getChildren().indexOf(tr);
				x = ((Number)tr).value;
				Term kid = tr.parent.getChildren().get(index-1);
				if(kid instanceof Minus){
					x *= -1;
				}
			}
		}
		else{
			if(tr.getChildren().get(1) instanceof Number){
				x = -((Number)tr.getChildren().get(1)).value;
			}
			else if(tr.getChildren().size()>2 && tr.getChildren().get(2) instanceof Number){
				x = -((Number)tr.getChildren().get(2)).value;
			}
		}


		return x;
	}

	public boolean SimpleCompound(){
		Term tr = this;
		boolean simp = false;
		if((tr.operator !=null)&&(tr.operator instanceof Times)&&(tr.getChildren().size()>2)&&!(tr.isRationalNumber())){
			boolean insidessimp = true;
			for(int i =0; i<tr.getChildren().size(); i++){
				if(!tr.getChildren().get(i).isSimple()){
					insidessimp = false;
				}
			}
			if(insidessimp){simp = true;}
		}

		return simp;
	}


	public boolean isNatural(){
		boolean natural = false;
		Term tr = this;

		if(tr instanceof Number){

			Number n = (Number) tr;

			if(Math.floor(n.value) == n.value  && n.value>0){
				natural = true;
			}

		}

		return natural;
	}

	public boolean isWhole(){
		boolean whole = false;
		Term tr = this;

		if(tr.isNatural()){
			whole = true;
		}
		if(tr instanceof Number){

			Number n = (Number)tr;
			if(n.value == 0 ){ whole = true;}

		}
		return whole;
	}

	public boolean isInteger(){
		boolean isint = false;
		Term tr = this;

		try {
			if(tr.isWhole()){isint = true;}

			else if(tr.getChildren().size() == 2 && tr.getChildren().get(0) instanceof Negative
					&& tr.getChildren().get(1) instanceof Number && tr.isNegative()){
				isint = true;
			}
			else if(tr.getChildren().size() == 3 && tr.getChildren().get(1) instanceof Negative
					&& tr.getChildren().get(2) instanceof Number && tr.isNegative()){
				isint = true;
			}
			if(tr instanceof Constant){
				Number n = (Number) tr;

				if(Math.floor(n.value) == n.value  && n.value>0){
					isint = true;
				}
				else{
					isint = false;
				}

			}
		} catch (Exception e) {}

		return isint;

	}

	public boolean isDecimal(){
		Term tr = this;
		boolean decimal = false;
		if(tr.isRealNumber()){
			if(!(tr instanceof Fraction)){
				decimal = true;
			}
		}
		else if(tr instanceof Number ||
				(tr instanceof NegativeTerm && tr.getAbsoluteValue() instanceof Number)){
			decimal = true;
		}
		if(tr instanceof Constant){
			decimal = false;
		}
		return decimal;
	}

	public boolean isRealNumber(){
		Term tr = this;
		boolean real = false;
		if(tr.isRationalNumber()){
			real = true;
		}
		else if(tr instanceof Constant){
			real = true;
		}
		else if(tr instanceof Number){
			real = true;
		}

		return real;
	}

	public boolean isRationalNumber(){
		Term tr = this;
		boolean rational = false;
		if(tr.isInteger()){
			rational = true;
		}
		else if (tr.operator instanceof Divide && tr.getChildren().size() == 3 &&
				tr.getChildren().get(0).isInteger() && tr.getChildren().get(2).isInteger()){
			rational = true;
		}
		return rational;
	}

	public boolean isSimpleFraction(){
		Term tr = this;
		boolean fraction = false;
		if (tr.operator instanceof Divide && tr.getChildren().size() == 3 &&
				tr.getChildren().get(0).isInteger() && tr.getChildren().get(2).isInteger()){
			fraction = true;
		}
		return fraction;
	}
	
	public boolean isFraction(){
		Term tr = this;
		boolean fraction = false;
		if(tr.isSimpleFraction()){
			fraction = true;
		}
		else if(tr instanceof Fraction){
			fraction = true;
		}
		else if(tr.operator instanceof Divide && tr.getChildren().size() == 3 ){
			fraction = true;
		}
		
		return fraction;
	}

	public Term toggleNegative(){
		Term mid;

		if(!this.isNegative()){
			NegativeTerm nt = new NegativeTerm(this);
			mid = nt;
		}
		else{
			mid = getAbsoluteValue();
		}

		return mid;
	}

	public Term getAbsoluteValue(){
		Term mid;

		if(isNegative()){
			if(this.getChildren().size()==2){
				mid = this.getChildren().get(1);
			}
			else if(this.getChildren().size()==3){
				mid = this.getChildren().get(2);
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

	public void setChildren(ArrayList<Term> children) {
		this.children = children;
	}

	public ArrayList<Term> getChildren() {
		return children;
	}

	public void setNegative(boolean isnegative) {
		this.isNegative = isnegative;
	}

	public boolean isNegative() {
		boolean ans = false;
		if(this.operator != null){
			if(this.operator instanceof Negative){
				ans= true;
			}

		}
		if(this instanceof NegativeTerm){
			ans = true;
		}
		return ans;
	}


	public void setScreenPositions(ArrayList<StringRectangle> screenPositions){
		//set the position of the containers of the term as they are drawn on a screen.

		AssignScreenPositions asp = new AssignScreenPositions(this, screenPositions);
	}




	private int steps = 0;
	private Term innerTerm;
	private boolean found = false;
	@Override
	public void performAction(Term tr ) {

		if(innerTerm.container.bl.x == tr.container.bl.x &&
				innerTerm.container.bl.y == tr.container.bl.y &&
				innerTerm.container.width == tr.container.width &&
				innerTerm.container.height == tr.container.height ){
			found = true;
		}
		if(!found){
			steps++;}
	}


	public int positionOfInnerTermDown(Term innerTerm){
		//if the term tree is laid out as a downwalk, this will return the integer position of any term.

		this.steps = 0;
		this.found = false;
		this.innerTerm = innerTerm;
		downwalk walk = new downwalk(this, this);
		if(!found){return -1;}
		else { return steps;}
	}

	public Term getResultOfOperation(){

		return getResultOfBasicOperation();

	}

	private Term getResultOfBasicOperation(){

		if(!this.isSimple()){
			Term result = this.operator.simpleOperation(this);

			return result;}
		else{
			return null;
		}
	}


}

