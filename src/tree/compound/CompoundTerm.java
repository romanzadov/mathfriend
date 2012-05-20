package tree.compound;

import java.util.ArrayList;
import java.util.List;

import container.walks.AssignScreenPositions;

import display.Rectangle;
import representTerms.StringRectangle;
import tree.Term;
import tree.functions.*;
import tree.simple.SimpleTerm;

public class CompoundTerm extends Term {

    private Class<? extends Function> function;
    private ArrayList<Term> children = new ArrayList<Term>();


    public CompoundTerm(Class<? extends Function> function) {
        this.function = function;
    }

    public void addChild(Term child) {
        children.add(child);
    }

    @Override
    public String toString() {
        String st = "";
        String operator = "";
        try {
            operator = function.newInstance().toString();
        } catch (Exception ignore) {

        }


        for (int i = 0; i < this.getChildren().size(); i++) {
            Term child = getChildren().get(i);
            if (child instanceof SimpleTerm) {
                st += "[";
            }
            if (child.isNegative()) {
                st += "-";
            }
            if (child.isInverse()) {
                st += "1/";
            }
            if (child.hasParentheses()) {
                st += "(" + child + ")";
            } else {
                st += child;
            }
            if (child instanceof SimpleTerm) {
                st += "]";
            }
            if (this.getChildren().indexOf(child) < this.getChildren().size() - 1) {
                st += operator;
            }
        }

        return st;
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

    public boolean isSimpleFraction() {
        CompoundTerm tr = this;
        boolean fraction = false;
        /*if (tr.getFunction() instanceof Divide && tr.getChildren().size() == 3 &&
                  tr.getChildren().get(0).isInteger() && tr.getChildren().get(2).isInteger()){
              fraction = true;
          }*/
        return fraction;
    }

    public boolean isFraction() {
        CompoundTerm tr = this;
        boolean fraction = false;
        /*if(tr.isSimpleFraction()){
              fraction = true;
          }
          else if(tr instanceof Fraction){
              fraction = true;
          }
          else if(tr.getFunction() instanceof Divide && tr.getChildren().size() == 3 ){
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
        for (Term child : children) {
            if (child instanceof CompoundTerm) {
                compoundTerms.add((CompoundTerm) child);
            }
        }
        return compoundTerms;
    }

    public void setScreenPositions(ArrayList<StringRectangle> screenPositions) {
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

    */
    public CompoundTerm getResultOfOperation() {
        return getResultOfBasicOperation();
    }


    private CompoundTerm getResultOfBasicOperation() {

        if (!this.isSimple()) {

            CompoundTerm result = this.getFunction().simpleOperation(this);

            return result;
        } else {
            return null;
        }
    }


    public Function getFunction() {
        Function instance = null;
        try {
            instance = function.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<StringRectangle> getRelativeRectangles() {

        return null;
    }

    @Override
    public Rectangle getContainer() {
        if(function.equals(Plus.class)) {
            return getPlusRectangle();
        } else if (function.equals(Equals.class)) {
            return getEqualsRectangle();
        }
        return null;
    }

    //draws the rectangle that goes around terms with a plus operator
    public Rectangle getPlusRectangle() {
        //check rectangled and get max height
        float maxHeight = 0;
        float widthSoFar = 0;
        for (Term child : getChildren()) {

            if (child.getContainer().getHeight() > maxHeight) {
                maxHeight = child.getContainer().getHeight();
            }

            widthSoFar += child.getContainer().getWidth();

        //    Rectangle left = child.getContainer();
        //    left.bl.y = maxHeight / 2 - left.height / 2;
        //    left.bl.x = widthSoFar;
        }

        return new Rectangle(widthSoFar, maxHeight);
    }

    public Rectangle getEqualsRectangle(){

			//find top height
			float ysofar = 0;
			float xsofar = 0;
			for(Term child: getChildren()){
				if(child.getContainer().getHeight() >ysofar){
					ysofar = child.getContainer().getHeight();
				}
                xsofar += child.getContainer().getWidth();
			}

			//allign
		/*	for(int i =0; i<tr.getChildren().size(); i++){
				Rectangle cont = tr.getChildren().get(i).getContainer();
				cont.bl.x = xsofar;
				xsofar += cont.getWidth();
				cont.bl.y = ysofar/2- cont.getHeight() /2;

			}*/

		return new Rectangle(xsofar, ysofar);
	}


    public Rectangle getExponentRectangle(CompoundTerm tr) {
        float xsofar = 0;
        float ysofar = 0;

            //check that all things are containered
    /*        for (int i = 0; i < tr.getChildren().size(); i++) {
                if (tr.getChildren().get(i).getContainer() == null) {
                    System.out.println("error: exponent was run on non-containered terms");
                }
            }


            xsofar = tr.getChildren().get(0).getContainer().width;
            ysofar = tr.getChildren().get(0).getContainer().height;

            for (int i = 2; i < tr.getChildren().size(); i += 2) {
                fontize ft = new fontize(tr.getChildren().get(i), .7);

            }

            for (int i = 2; i < tr.getChildren().size(); i += 2) {
                rectangle cn = tr.getChildren().get(i).getContainer();

                cn.bl.x = xsofar;
                cn.bl.y = ysofar;
                xsofar += cn.width;
                ysofar += cn.height / 2;
                if (i == tr.getChildren().size() - 1) {
                    ysofar += cn.height / 2;
                }
            }*/

        return new Rectangle(xsofar, ysofar);

    }
}

