package tree;

import java.util.ArrayList;
import java.util.List;

import tree.compound.CompoundTerm;

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
		return null;
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
