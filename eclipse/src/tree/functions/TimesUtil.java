package tree.functions;

import tree.Term;
import tree.compound.CompoundTerm;
import tree.compound.Fraction;

public class TimesUtil {
	
	public static CompoundTerm divideTerms(Term x, Term y) {
		if (y.isFraction()) {
			return multiplyTerms(x, Fraction.getInverse(y));
		}
		if (y.isInverse()) {
			y.setInverse(false);
			return multiplyTerms(x, y);
		}
		return new Fraction(x, y);
	}
	
	public static CompoundTerm multiplyTerms(Term x, Term y) {
		CompoundTerm multiple = new CompoundTerm(Function.TIMES);
		if (x.isAdditive()) {
			x.setHasParentheses(true);
		}
		if (y.isAdditive() || y.isNegative()) {
			y.setHasParentheses(true);
		}
		
		if (x.isMultiplicative()) {
			multiple.setChildren(((CompoundTerm)x).getChildren());
		} else {
			multiple.addChild(x);
		}
		if (y.isMultiplicative()) {
			for(Term child: ((CompoundTerm)y).getChildren()) {
				multiple.addChild(child);
			}
		} else {
			multiple.addChild(y);
		}
		
		return multiple;
	}
	
}
