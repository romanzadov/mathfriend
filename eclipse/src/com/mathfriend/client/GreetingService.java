package com.mathfriend.client;

import tree.compound.CompoundTerm;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String getFirstCompoundTermHtml(String name) throws IllegalArgumentException;

	String getMovedTerm(int downId, int ghostId);
}
