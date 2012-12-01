package com.mathfriend.client;

import tree.compound.CompoundTerm;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String name, AsyncCallback<String> callback);
}
