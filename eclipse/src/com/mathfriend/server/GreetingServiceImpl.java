package com.mathfriend.server;

import java.util.ArrayList;
import java.util.List;

import tree.Term;
import tree.TermUtil;
import tree.compound.CompoundTerm;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mathfriend.client.GreetingService;


@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	private List<CompoundTerm> terms = new ArrayList<CompoundTerm>();

	public String getFirstCompoundTermHtml(String input) throws IllegalArgumentException {
	
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		CompoundTerm term = (CompoundTerm)Term.getTermFromString(input);		
		terms.add(term);
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
	public String getMovedTerm(int downId, int ghostId) {
		CompoundTerm lastTerm = terms.get(terms.size() - 1);
		Term down = TermUtil.getDescendantById(lastTerm, downId);
		Term ghost = TermUtil.getDescendantById(lastTerm, ghostId);
		if (down == null  || ghost == null) {
			return null;
		}
		return TermUtil.getMoveResult(lastTerm, down, ghost).toHtml();
	}



	@Override
	public String performOperation(int operatorId, int dataId) {
		CompoundTerm lastTerm = terms.get(terms.size() - 1);
		try {
			CompoundTerm operand = (CompoundTerm)TermUtil.getDescendantById(lastTerm, dataId);
			return TermUtil.getOperationResult(lastTerm, operand, operatorId).toHtml();
		} catch (ClassCastException e) {
			
		}
			
		return null;
	}
}
