package com.mathfriend.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mathfriend.exception.NullParentException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String getFirstCompoundTermHtml(String formula);

	String getMovedTerm(int downId, int ghostId);

	String performOperation(int operatorId, int dataId);

	String undoStep();
}
