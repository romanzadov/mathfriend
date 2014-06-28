package tree;


import java.util.ArrayList;
import java.util.List;

import parse.ParseCharacterUtil;
import parse.PreSimpleTerm;
import parse.PreSimpleUtil;
import tree.compound.CompoundTerm;
import tree.compound.Fraction;
import tree.functions.Function;
import tree.simple.Number;
import tree.simple.Variable;
import tree.simple.SimpleTerm;

public abstract class Term {

    private CompoundTerm parent;
    private boolean hasParentheses = false;
	private boolean isNegative = false;
    private boolean isInverse = false;

    public static Term getTermFromString(String st) {
        ArrayList<Character> characters = ParseCharacterUtil.getCharacterArrayFromString(st);
        PreSimpleUtil preSimpleUtil = new PreSimpleUtil();
        List<PreSimpleTerm> preSimpleTerms = preSimpleUtil.simplify(characters);
        return getTerm(preSimpleTerms);
    }

    public String toHtml() {
    	return this.toString();
    }
    
    protected static Term getTerm(List<PreSimpleTerm> preSimpleTerms) {
        Term term = null;

        if (preSimpleTerms.size() == 1) {
            term = SimpleTerm.getSimpleTerm(preSimpleTerms.get(0));
        } else if (preSimpleTerms.size() == 2) {
            PreSimpleTerm first = preSimpleTerms.get(0);
            PreSimpleTerm second = preSimpleTerms.get(1);
            if (PreSimpleTerm.Type.FUNCTION.equals(first.getType()) &&
                    PreSimpleTerm.FunctionType.NEGATIVE.equals(first.getFunctionType())) {

                term = SimpleTerm.getSimpleTerm(second);
                term.setNegative(true);
            } else if (PreSimpleTerm.Type.FUNCTION.equals(first.getType()) &&
                    PreSimpleTerm.FunctionType.DIVIDE.equals(first.getFunctionType())) {

                term = SimpleTerm.getSimpleTerm(second);
                term.setInverse(true);
            }
        }
        
        if (term == null) {
            Function function = TermContsructionUtil.getHighestPriorityFunction(preSimpleTerms);
            term = new CompoundTerm(function);

            List<PreSimpleTermGrouping> groupings = TermContsructionUtil.getGroupings(preSimpleTerms, function);
            for (PreSimpleTermGrouping grouping : groupings) {

                Term child = getTerm(grouping.getPreSimpleTerms());
                if (child != null) {
            		child.setParent((CompoundTerm)term);
                	if(!child.isNegative()) {child.setNegative(grouping.isNegative());}
                    if(!child.isInverse()) {child.setInverse(grouping.isInverse());}
                    if(!child.hasParentheses()) {child.setHasParentheses(grouping.hasParentheses());}
                    ((CompoundTerm)term).addChild(child);
                }
            }
            if (function.equals(Function.TIMES)) {
            	ArrayList<Term> children = ((CompoundTerm)term).getChildren();
            	ArrayList<Term> newChildren = new ArrayList<Term>();
            	for (int i = 0; i<children.size(); i++) {
            		Term child = children.get(i);
            		if (child.isInverse()) {
            			newChildren.add(Fraction.getOneOverTerm(child));
            		} else if (i == children.size() - 1) 
            			newChildren.add(child);
            		else {
            			Term nextChild = children.get(i + 1);
            			if (nextChild.isInverse()) {
            				Fraction fraction = new Fraction(child, nextChild);
	            			newChildren.add(fraction);
	            			i = i+1;
            			} else {
            				newChildren.add(child);
            			}
            		}
            	}
            	if (newChildren.size() == 1) {
            		term = newChildren.get(0);
            	} else {
            		((CompoundTerm)term).setChildren(newChildren);
            	}
            }
        }
        
        
        return term;
    }

    public CompoundTerm getParent() {
        return parent;
    }

    public void setParent(CompoundTerm parent) {
        this.parent = parent;

    }

    public boolean hasParentheses() {
        return hasParentheses;
    }

    public void setHasParentheses(boolean hasParentheses) {
        this.hasParentheses = hasParentheses;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public void setNegative(boolean negative) {
        isNegative = negative;
    }

    public void toggleNegative() {
    	isNegative = !isNegative;
    }
    
    public boolean isInverse() {
    	boolean isDenominator = false;
    	if (getParent() != null && getParent().isFraction()) {
    		if (((Fraction)getParent()).getDenominator().equals(this)) {
    			isDenominator = true;
    		}
    	}
        return isInverse || isDenominator;
    }

    public void setInverse(boolean inverse) {
        isInverse = inverse;
    }
    
    public boolean isAdditive() {
    	return (this instanceof CompoundTerm && ((CompoundTerm)this).getFunction().equals(Function.PLUS));
    }
    
    public boolean isMultiplicative() {
    	return (this instanceof CompoundTerm && ((CompoundTerm)this).getFunction().equals(Function.TIMES));
    }
    
    public boolean isEquality() {
    	return (this instanceof CompoundTerm && ((CompoundTerm)this).getFunction().equals(Function.EQUALS));
    }

    public boolean isSimple() {
        return this instanceof SimpleTerm;
    }

    public boolean isFraction() {
    	return this instanceof Fraction;
    }
    
    public boolean isNumber() {
    	return this instanceof Number;
    }
    
    public boolean isVariable() {
    	return this instanceof Variable;
    }
    
    public boolean isNumber(double value) {
    	if (this instanceof Number) {
    		Number n = (Number)this;
    		if (n.absoluteValue == Math.abs(value)) {
    			if (value == 0) {
    				return true;
    			}
    			return n.isNegative() == (value < 0);
    		}
    	}
    	return false;
    }
}
 