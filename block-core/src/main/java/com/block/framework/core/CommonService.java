package com.block.framework.core;

import com.block.framework.core.context.RequestContext;

public class CommonService {

	public String getMethodName(){
		return Thread.currentThread().getStackTrace()[1].getMethodName();
	}
	
	public <T> T getContextValue(String key){
		return RequestContext.<T>getValue(key);
	}
}
