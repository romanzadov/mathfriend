package com.mathfriend.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String getFirstCompoundTermHtml(String formula);

	TermResponse getMovedTerm(int downId, int ghostId);

	TermResponse performOperation(int operatorId, int dataId);

	String undoStep();
}
