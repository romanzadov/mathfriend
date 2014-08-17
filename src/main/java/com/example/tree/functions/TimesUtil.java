package com.example.tree.functions;

import com.example.tree.Term;
import com.example.tree.compound.CompoundTerm;
import com.example.tree.compound.Fraction;
import com.example.tree.simple.Number;

public class TimesUtil {
	
	public static Term divideNumbers(Term a, Term b) {
		if (a.isNumber() && b.isNumber() && b.isInverse()) {
			Number product = new Number( ((Number)a).getValue() /  ((Number)b).getValue());
			product.setParent(null);
			return product;
		}
		throw new RuntimeException("Dividing numbers not possible.");
	}
	
	public static Term multiplyNumbers(Term a, Term b) {
		if (a.isNumber() && b.isNumber()) {
			Number product = new Number( ((Number)a).getValue() *  ((Number)b).getValue());
			product.setParent(null);
			return product;
		} else if (a.isFraction() || b.isFraction()) {
			if (!a.isFraction()) {
				Fraction second = (Fraction)b;
				if (a.isInverse()) {
					return new Fraction(second.getNumerator(), multiplyTerms(a, second.getDenominator()));
				} else {
					return new Fraction(multiplyTerms(a, second.getNumerator()), second.getDenominator());
				}
			} else if (!b.isFraction()) {
				Fraction first = (Fraction)a;
				if (b.isInverse()) {
					return new Fraction(first.getNumerator(), multiplyTerms(first.getDenominator(), b));
				} else {
					return new Fraction(multiplyTerms(first.getNumerator(), b), first.getDenominator());
				}
			} else {
				Fraction first = (Fraction)a;
				Fraction second = (Fraction)b;
				return new Fraction(multiplyTerms(first.getNumerator(), second.getNumerator()), multiplyTerms(first.getDenominator(), second.getDenominator()));
			}
		} else if (a.isNumber() || b.isNumber()) {
			return getDistributed(a, b);
		}
		throw new RuntimeException("Multiplying not numbers");
	}
	
	private static CompoundTerm getDistributed(Term a, Term b) {
		CompoundTerm product = new CompoundTerm(Function.PLUS);
		final CompoundTerm additive;
		final Term multiplyer;
		if(a.isAdditive()) {
			additive = (CompoundTerm) a;
			multiplyer = b;
		} else if (b.isAdditive()) {
			additive = (CompoundTerm) b;
			multiplyer = a;
		} else {
			return multiplyTerms(a, b);
		}
		for (Term child: additive.getChildren()) {
			product.addChild(multiplyTerms(Term.getTermFromString(multiplyer.toString()), child));
		}
		return product;
	}
	
	public static CompoundTerm divideTerms(Term x, Term y) {
		if (y.isFraction() || x.isFraction()) {
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
		
		if (x.isMultiplicative() && !x.isFraction()) {
			multiple.setChildren(((CompoundTerm)x).getChildren());
		} else {
			multiple.addChild(x);
		}
		if (y.isMultiplicative() && !y.isFraction()) {
			for(Term child: ((CompoundTerm)y).getChildren()) {
				multiple.addChild(child);
			}
		} else {
			multiple.addChild(y);
		}
		return multiple;
	}
	

	
}
