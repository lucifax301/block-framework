package com.block.framework.core.dubbo;

import java.io.Serializable;

import com.block.framework.core.context.RequestContext;

public class BlockInvokeContext implements Serializable {

	private RequestContext requestContext;

	public RequestContext getRequestContext() {
		return requestContext;
	}

	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}
	
	
}
