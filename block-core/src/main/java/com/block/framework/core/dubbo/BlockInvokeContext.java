package com.block.framework.core.dubbo;

import java.io.Serializable;

import com.block.framework.core.context.RequestContext;

public class BlockInvokeContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8998798343266471977L;
	private RequestContext requestContext;

	public RequestContext getRequestContext() {
		return requestContext;
	}

	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}
	
	
}
