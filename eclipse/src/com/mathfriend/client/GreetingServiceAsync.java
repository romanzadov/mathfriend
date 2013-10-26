package com.mathfriend.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void getFirstCompoundTermHtml(String formula, AsyncCallback<String> callback);
	void getMovedTerm(int downId, int ghostId, AsyncCallback<TermResponse> callback);
	void performOperation(int operatorId, int dataId, AsyncCallback<TermResponse> asyncCallback);
	void undoStep(AsyncCallback<String> asyncCallback);
}
