package tree;

import java.util.ArrayList;
import java.util.List;

import com.mathfriend.exception.NullParentException;

import tree.compound.CompoundTerm;
import tree.functions.Function;
import tree.functions.PlusUtil;

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
	
	public static Term getOperationResult(CompoundTerm term, Term start, Term end) {
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
				default:
			}
			
		}
		return null;
	}
	
	private static void addTerms(Term start, Term end) throws NullParentException{
		removeTerm(start);
		CompoundTerm parent = end.getParent();
		
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
	
	
}