package com.block.framework.core.dubbo;

import java.util.Map;

import com.alibaba.dubbo.rpc.Result;

public class RpcResultWrapper implements Result, BlockContextable {

	private Result result;
	private BlockInvokeContext invokeContext;
	
	@Override
	public BlockInvokeContext getInvokeContext() {
		return invokeContext;
	}

	@Override
	public void setInvokeContext(BlockInvokeContext invokeContext) {
		this.invokeContext=invokeContext;

	}
	
	public RpcResultWrapper(Result result,BlockInvokeContext context){
		this.invokeContext=context;
		this.result=result;
	}
	
	public Result getWrappedResult(){
		return result;
	}

	@Override
	public Object getValue() {
		return result.getValue();
	}

	@Override
	public Throwable getException() {
		return result.getException();
	}

	@Override
	public boolean hasException() {
		return result.hasException();
	}

	@Override
	public Object recreate() throws Throwable {
		return result.recreate();
	}

	@Override
	public Object getResult() {
		return result.getResult();
	}

	@Override
	public Map<String, String> getAttachments() {
		return result.getAttachments();
	}

	@Override
	public String getAttachment(String key) {
		return result.getAttachment(key);
	}

	@Override
	public String getAttachment(String key, String defaultValue) {
		return result.getAttachment(key, defaultValue);
	}

}
