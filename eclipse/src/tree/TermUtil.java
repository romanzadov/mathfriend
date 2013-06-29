package tree;

import java.util.ArrayList;
import java.util.List;

import tree.compound.CompoundTerm;
import tree.functions.Function;
import tree.functions.PlusUtil;
import tree.functions.TimesUtil;

import com.mathfriend.exception.NullParentException;

public class TermUtil {
	
	public static Term getDescendantById(CompoundTerm term, int id) {
		List<Term> terms = getDescendants(term);
		for (Term child: terms) {
			if(child.hashCode() == id) {
				return child;
			}
		}
		return null;
	}
	
	private static List<Term> getDescendants(CompoundTerm term) {
		List<Term> terms = new ArrayList<Term>();
		terms.add(term);
		for (Term child: term.getChildren()) {
			terms.add(child);
			if (!child.isSimple()){
				terms.addAll(getDescendants(((CompoundTerm)child)));
			}
		}
		return terms;
	}
	
	public static Term getOperationResult(CompoundTerm term, CompoundTerm operand, int operatorId) {
		if (operatorId < operand.getChildren().size() - 1) {
			Term a = term.getChildren().get(operatorId);
			Term b = term.getChildren().get(operatorId + 1);
			
			switch (operand.getFunction()) {
				case PLUS: {
					getAddResult(term, a, b);
					return term;
				}
				case TIMES: {
					
				}
				default :
			}
			
		}
		return null;
	}
	
	private static void getAddResult(CompoundTerm term, Term a, Term b) {
		CompoundTerm parent = a.getParent();
		int position = parent.getChildren().indexOf(a);
		parent.removeChild(a);
		parent.removeChild(b);
		Term sum = PlusUtil.addTermValues(a, b);
		if (parent.getChildren().size() == 0) {
			CompoundTerm grandparent = parent.getParent();
			grandparent.setChild(grandparent.getChildren().indexOf(parent), sum);
		} else {
			parent.insertChild(position, sum);
		}
	}
	
	public static Term getMoveResult(CompoundTerm term, Term start, Term end) {
		Term startSibling = getSibling(start, end);
		if (startSibling != null) {
			return switchSiblings(term, start, startSibling);
		}
		Term overEqualsSibling = getOverEqualsSibling(start, end);
		if (overEqualsSibling != null) {
			try {
				return switchOverEquals(term, start, overEqualsSibling);
			} catch (NullParentException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	private static Term getOverEqualsSibling(Term start, Term end) {
		if (Function.EQUALS.equals(start.getParent().getFunction())) {
			return getSibling(start, end);
		} else if (Function.EQUALS.equals(start.getParent().getParent().getFunction())) {
			return getSibling(start.getParent(), end);
		}
		return null;
	}
	
	private static CompoundTerm switchOverEquals(CompoundTerm term, Term start, Term end) throws NullParentException {
		if (Function.EQUALS.equals(start.getParent().getFunction())) {
			return switchSiblings(term, start, end);
		} else if (Function.EQUALS.equals(start.getParent().getParent().getFunction())) {
			Function function = start.getParent().getFunction();
			switch (function) {
				case PLUS:
					addTerms(start, end);
					return term;
				case TIMES:
					divideTerms(start, end);
					return term;
				default:
			}
		}
		return null;
	}
	
	private static void divideTerms(Term start, Term end) throws NullParentException {
		removeTerm(start);
		CompoundTerm parent = end.getParent();
		
		CompoundTerm dividend = TimesUtil.divideTerms(end, start);
		dividend.setParent(parent);
		parent.setChild(parent.getChildren().indexOf(end), dividend);
	}
	
	private static void addTerms(Term start, Term end) throws NullParentException{
		CompoundTerm parent = end.getParent();
		removeTerm(start);
		
		CompoundTerm sum = PlusUtil.subtractTerms(end, start);		
		sum.setParent(parent);
		parent.setChild(parent.getChildren().indexOf(end), sum);
	}
	
	private static void removeTerm(Term extra) throws NullParentException {
		CompoundTerm parent = extra.getParent();
		if (parent == null) {
			throw new NullParentException();
		}
		
		if (parent.getChildren().size() == 1) {
			removeTerm(parent);
		} else if (parent.getChildren().size() == 2 && parent.getFunction().numberOfInputs == 2) {
			CompoundTerm grandparent = parent.getParent();
			if (grandparent == null) {
				throw new NullParentException();
			}
			List<Term> children = parent.getChildren();
			children.remove(extra);
			Term sibling  = children.get(0);
			grandparent.setChild(grandparent.getChildren().indexOf(parent), sibling);
		}
		
		List<Term> children = parent.getChildren();
		children.remove(extra);
		extra.setParent(null);
	}
	
	private static CompoundTerm switchSiblings(CompoundTerm term, Term start, Term end) {
		CompoundTerm parent = start.getParent();
		int startIndex = parent.getChildren().indexOf(start);
		int endIndex = parent.getChildren().indexOf(end);
		parent.setChild(startIndex, end);
		parent.setChild(endIndex, start);
		return term;
	}
	
	private static Term getSibling(Term self, Term relative) {
		
		if (relative.getParent().equals(self.getParent())) {
			return relative;
		}
		
		List<CompoundTerm> parents = new ArrayList<CompoundTerm>();
		CompoundTerm parent = relative.getParent();
		while (parent != null) {
			parents.add(parent);
			parent = parent.getParent();
		}
		for (CompoundTerm term: parents) {
			if (self.getParent().equals(term.getParent())){
				return term;
			}
		}
		return null;
	}
	
    public static boolean canBeMultiplied(Term a, Term b) {
    	return a.isNumber() && b.isNumber();
    }
    
    public static boolean canBeAdded(Term a, Term b) {
    	return a.isNumber() && b.isNumber();
    }
}
