package com.block.framework.common.exception;

public class NoAuthException extends BlockException {

	public NoAuthException() {
		super();
	}

	public NoAuthException(String arg0) {
		super(arg0);
	}
	
	public NoAuthException(String arg0,String arg1){
		super(arg0+":"+arg1);
	}
	
	public NoAuthException(String arg0,Throwable t){
		super(arg0,t);
	}
	
	public NoAuthException(Throwable t){
		super(t);
	}
}
