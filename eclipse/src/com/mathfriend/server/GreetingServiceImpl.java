package com.mathfriend.server;

import java.util.ArrayList;
import java.util.List;

import tree.Term;
import tree.TermUtil;
import tree.compound.CompoundTerm;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mathfriend.client.GreetingService;
import com.mathfriend.client.TermResponse;
import com.mathfriend.exception.NullParentException;


@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	private List<String> terms = new ArrayList<String>();
	private CompoundTerm lastTerm = null;
	
	public String getFirstCompoundTermHtml(String input) throws IllegalArgumentException {
		terms = new ArrayList<String>();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		CompoundTerm term = (CompoundTerm)Term.getTermFromString(input);		
		setLastTerm(term);
		
		return term.toHtml();
	}
	
	
	
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}



	@Override
	public TermResponse getMovedTerm(int downId, int ghostId) {
		CompoundTerm lastTerm = getLastTerm();
		Term down = TermUtil.getDescendantById(lastTerm, downId);
		Term ghost = TermUtil.getDescendantById(lastTerm, ghostId);
		if (down == null  || ghost == null) {
			throw new RuntimeException("Unable to move.");
		}
		try {
			CompoundTerm movedTerm = (CompoundTerm)TermUtil.getMoveResult(lastTerm, down, ghost);
			setLastTerm(movedTerm);
			return new TermResponse(movedTerm.toHtml(), movedTerm.toString());
		} catch (NullParentException e) {
			throw new RuntimeException("Unable to move.");
		}
	}



	@Override
	public TermResponse performOperation(int operatorId, int dataId) {
		CompoundTerm lastTerm = getLastTerm();
		try {
			CompoundTerm operand = (CompoundTerm)TermUtil.getDescendantById(lastTerm, dataId);
			CompoundTerm operationResult = (CompoundTerm)TermUtil.getOperationResult(lastTerm, operand, operatorId);
			setLastTerm(operationResult);
			return new TermResponse(operationResult.toHtml(), operationResult.toString());
		} catch (ClassCastException e) {
			
		}
		throw new RuntimeException("Unable to perform operation.");
	}
	
	private void setLastTerm(Term term) {
		terms.add(term.toString());
		lastTerm = (CompoundTerm)term;
	}
	
	private CompoundTerm getLastTerm() {
		return lastTerm;
	}



	@Override
	public String undoStep() {
		if (terms.size() > 1) {
			terms.remove(terms.size() -1);
			CompoundTerm term = (CompoundTerm)Term.getTermFromString(terms.get(terms.size()-1));		
			lastTerm = term;
			return getLastTerm().toHtml();
		}
		return null;
	}
}
