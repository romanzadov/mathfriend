package tree.compound;

import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.taskdefs.Javadoc.Html;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.google.gwt.user.client.ui.Button;

import container.walks.AssignScreenPositions;

import representTerms.StringRectangle;
import tree.Term;
import tree.functions.*;
import tree.simple.SimpleTerm;

public class CompoundTerm extends Term {

	private Class<? extends Function> function;
	private ArrayList<Term> children = new ArrayList<Term>();


    public CompoundTerm(Class<? extends Function> function){
        this.function = function;
    }

    public void addChild(Term child) {
        children.add(child);
    }

	@Override
	public String toString(){
		
        String html = "<mrow %s>%s</mrow>";
        if (this.hasParentheses()) {
        	 html = "<mfenced separators=\'\' %s>%s</mfenced>";
        }
        List<String> classes = new ArrayList<String>();
        classes.add("term");
    	classes.add("compoundTerm");
    	classes.add(function.getSimpleName());
    	
        String content = "";
        
        for(int i = 0; i<this.getChildren().size(); i++){
        	
            content += getContent(getChildren().get(i));
            if(i < this.getChildren().size() - 1) {
            	String operator = "";
            	if (function == Equals.class) {
            		operator = "=";
            	} else if (function == Plus.class) {
            		Term nextChild = this.getChildren().get(i + 1);
            		if (nextChild.isNegative()) {
            			operator = "-";
            		} else {
            			operator = "+";
            		}
            	} 
            	if (content != "") {
            		content += "<mo>" + operator + "</mo>";
            	}	
            }
        }
        
        String classList = "";
        classList += "class=\'";
    	for(String c: classes) {
    		classList += c + " ";
    	}
    	classList += "\'";
    	
        return String.format(html, classList, content);        
	}
	
	protected String getContent(Term child) {
		if (child.isSimple()) {
	        String html = "<span %s>" + child.toString() + "</span>";
	        if (child instanceof tree.simple.Variable) {
		        html = "<mi %s>" + child.toString() + "</mi>";
	        } else if (child instanceof tree.simple.Number) {
		        html = "<mn %s>" + child.toString() + "</mn>";
	        }
	        
	        List<String> classes = new ArrayList<String>();
	        classes.add("term");
	        classes.add(child.getClass().getSimpleName());
	        if (child.isInverse()) {
	        	classes.add("inverse");
	        }

	        String classList = "";
	        classList += "class=\'";
	    	for(String c: classes) {
	    		classList += c + " ";
	    	}
	    	classList += "\'";
	    	
	        return String.format(html, classList);   
		} else {
			return child.toString();
		}
	}

/*
	public CompoundTerm MultiplyLikeTerms(CompoundTerm tr){
		CompoundTerm ans = null;
		CompoundTerm sel = this;

		if(sel.SimpleCompound() && tr.SimpleCompound()){

		}

		else if (sel.isSimpleFraction() || tr.isSimpleFraction()){
			MultiplyFractions mf = new MultiplyFractions();
			ans = mf.times(sel, tr);

		}
		eltree.functions se if (sel.isInteger() && tr.isInteger()){
			CompoundTerm n = new CompoundTerm();
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
*/

/*
	public CompoundTerm AddLikeTerms(CompoundTerm tr){

		CompoundTerm ans = null;

		CompoundTerm sel = this;
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
			CompoundTerm n = new CompoundTerm();
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
*/

/*	public Fraction getReciprical(){
		
		CompoundTerm a = new CompoundTerm();
		try {
			a = (CompoundTerm)this.clone();
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
	*/
	
	/*public static Fraction fractionOverOne(CompoundTerm tr){
		
		//take a term and put it over one
		
		Number one = new Number(1);
		Fraction f = new Fraction(tr, one);
		return f;
		
	}*/
	
	/*public static double getNumericValue(CompoundTerm tr){
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

*//*
				x = ((Number)tr).value;
				int positionOfX = tr.getParent().getChildren().indexOf(tr);
				
				if(positionOfX == 0){
					
					if(tr.isNegative()){
						x*= -1;
					}
					
					return x;
				}
				else{
					if(tr.getParent().getChildren().get(positionOfX - 1) instanceof Minus){
						x*= -1;
					}
				}
*//*

			}
		}
*/


	/*	if(!tr.isNegative()){
			if( tr.getParent().getChildren().indexOf(tr) == 0){
				if(tr instanceof Number){
					x = ((Number)tr).value;
				}
			}
			else{
				int index = tr.getParent().getChildren().indexOf(tr);
				x = ((Number)tr).value;
				Term kid = tr.getParent().getChildren().get(index-1);
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
		}*/

/*

		return x;
	}
*/


/*

	}*/

	public boolean isSimpleFraction(){
		CompoundTerm tr = this;
		boolean fraction = false;
		/*if (tr.getFunction() instanceof Divide && tr.getChildren().size() == 3 &&
				tr.getChildren().get(0).isInteger() && tr.getChildren().get(2).isInteger()){
			fraction = true;
		}*/
		return fraction;
	}

    public void insertChild(int index, CompoundTerm child) {
        children.add(index, child);
        child.setParent(this);
    }

    public void setChild(int index, CompoundTerm child) {
        children.set(index, child);
        child.setParent(this);
    }

	public void setChildren(ArrayList<Term> children) {
		this.children = children;
	}

	public ArrayList<Term> getChildren() {
		return children;
	}

    public ArrayList<CompoundTerm> getCompoundChildren() {
        ArrayList<CompoundTerm> compoundTerms = new ArrayList<CompoundTerm>();
        for(Term child: children) {
            if(child instanceof CompoundTerm) {
                compoundTerms.add((CompoundTerm)child);
            }
        }
        return compoundTerms;
    }

	public void setScreenPositions(ArrayList<StringRectangle> screenPositions){
		//set the position of the containers of the term as they are drawn on a screen.

		AssignScreenPositions asp = new AssignScreenPositions(this, screenPositions);
	}


/*

	private int steps = 0;
	private Term innerTerm;
	private boolean found = false;

    @Override
	public void performAction(Term tr ) {

		if(innerTerm.getContainer().bl.x == tr.getContainer().bl.x &&
				innerTerm.getContainer().bl.y == tr.getContainer().bl.y &&
				innerTerm.getContainer().width == tr.getContainer().width &&
				innerTerm.getContainer().height == tr.getContainer().height ){
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

*/	public CompoundTerm getResultOfOperation(){
		return getResultOfBasicOperation();
	}


	private CompoundTerm getResultOfBasicOperation(){

		if(!this.isSimple()){

			CompoundTerm result = this.getFunction().simpleOperation(this);

			return result;}
		else{
			return null;
		}
	}



    public Function getFunction() {
        Function instance = null;
        try {
            instance = function.newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected StringRectangle getStringRectangle() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

