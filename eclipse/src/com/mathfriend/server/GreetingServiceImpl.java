package com.mathfriend.server;

import java.util.ArrayList;
import java.util.List;

import tree.Term;
import tree.TermUtil;
import tree.compound.CompoundTerm;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mathfriend.client.GreetingService;


@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	private List<CompoundTerm> terms = new ArrayList<CompoundTerm>();

	public String getFirstCompoundTermHtml(String input) throws IllegalArgumentException {
	
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		CompoundTerm term = (CompoundTerm)Term.getTermFromString(input);		
		terms.add(term);
		return term.toString();
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
		return TermUtil.getOperationResult(lastTerm, down, ghost).toString();
	}
}
