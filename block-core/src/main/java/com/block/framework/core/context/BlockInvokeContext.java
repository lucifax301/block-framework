package com.block.framework.core.context;

import java.io.Serializable;

public class BlockInvokeContext implements Serializable {

	private RequestContext requestContext;

	public RequestContext getRequestContext() {
		return requestContext;
	}

	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}
	
	
}
