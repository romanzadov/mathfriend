package tree;


import parse.ParseCharacterUtil;
import parse.PreSimpleTerm;
import parse.PreSimpleUtil;
import tree.compound.CompoundTerm;
import tree.functions.Function;
import display.rectangle;
import tree.simple.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Term implements Cloneable{

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
            }
        }

        if (term == null) {
            Class<? extends Function> function = TermContsructionUtil.getHighestPriorityFunction(preSimpleTerms);
            term = new CompoundTerm(function);

            List<PreSimpleTermGrouping> groupings = TermContsructionUtil.getGroupings(preSimpleTerms, function);
            for (PreSimpleTermGrouping grouping : groupings) {

                Term child = getTerm(grouping.getPreSimpleTerms());
                if (child != null) {
                    child.setNegative(grouping.isNegative());
                    child.setInverse(grouping.isInverse());
                    child.setHasParentheses(grouping.hasParentheses());
                    ((CompoundTerm)term).addChild(child);
                }
            }
        }

        return term;
    }


    @Override
	public Object clone() throws CloneNotSupportedException {
		String st = this.toString();
		Term clone = getTermFromString(st);

		return clone;
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

    public boolean isInverse() {
        return isInverse;
    }

    public void setInverse(boolean inverse) {
        isInverse = inverse;
    }

    public boolean isSimple() {
        if (this instanceof SimpleTerm) {
            return true;
        }
        return false;
    }

    public rectangle getContainer() {
        return null;
    }

    public void setContainer(rectangle rectangle) {
        
    }
}
