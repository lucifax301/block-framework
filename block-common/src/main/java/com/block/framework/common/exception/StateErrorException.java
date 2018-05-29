package com.block.framework.common.exception;

public class StateErrorException extends BlockException {

	public StateErrorException() {
		super();
	}

	public StateErrorException(String arg0) {
		super(arg0);
	}
	
	public StateErrorException(String arg0,String arg1){
		super(arg0+":"+arg1);
	}
	
	public StateErrorException(String arg0,Throwable t){
		super(arg0,t);
	}
	
	public StateErrorException(Throwable t){
		super(t);
	}
}
