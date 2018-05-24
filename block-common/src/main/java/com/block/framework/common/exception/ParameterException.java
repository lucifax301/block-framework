package com.block.framework.common.exception;

public class ParameterException extends BlockException {

	public ParameterException() {
		super();
	}

	public ParameterException(String arg0) {
		super(arg0);
	}
	
	public ParameterException(String arg0,String arg1){
		super(arg0+":"+arg1);
	}
	
	public ParameterException(String arg0,Throwable t){
		super(arg0,t);
	}
	
	public ParameterException(Throwable t){
		super(t);
	}
}
