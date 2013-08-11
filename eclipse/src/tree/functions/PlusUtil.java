package tree.functions;

import tree.Term;
import tree.compound.CompoundTerm;
import tree.simple.Number;

public class PlusUtil {
	
	public static Number addTermValues(Term x, Term y) {
		if (x.isNumber() && y.isNumber()) {
			Number sum = new Number(((Number)x).getValue() + ((Number)y).getValue());
			sum.setParent(null);
			return sum;
		}
		throw new RuntimeException("Adding not numbers");
	}

	public static CompoundTerm addTerms(Term x, Term y) {
		
		CompoundTerm sum = new CompoundTerm(Function.PLUS);
		if (x.isAdditive()) {
			for (Term child: ((CompoundTerm)x).getChildren()) {
				sum.addChild(child);
			}
		} else {
			sum.addChild(x);
		}
		
		if (y.isAdditive()) {
			for (Term child: ((CompoundTerm)y).getChildren()) {
				sum.addChild(child);
			}
		} else {
			sum.addChild(y);
		}
		
		return sum;
	}
	
	public static CompoundTerm subtractTerms(Term x, Term y) {
		
		CompoundTerm difference = new CompoundTerm(Function.PLUS);
		if (x.isAdditive()) {
			for (Term child: ((CompoundTerm)x).getChildren()) {
				difference.addChild(child);
			}
		} else {
			difference.addChild(x);
		}
		
		if (y.isAdditive()) {
			for (Term child: ((CompoundTerm)y).getChildren()) {
				child.toggleNegative();
				difference.addChild(child);
			}
		} else {
			y.toggleNegative();
			difference.addChild(y);
		}
		
		return difference;
	}
}
