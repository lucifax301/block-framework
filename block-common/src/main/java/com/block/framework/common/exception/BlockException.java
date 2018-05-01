package com.block.framework.common.exception;

public class BlockException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2595084918847615351L;

	public BlockException() {
		super();
	}

	public BlockException(String arg0) {
		super(arg0);
	}
	
	public BlockException(String arg0,Throwable t){
		super(arg0,t);
	}
	
	public BlockException(Throwable t){
		super(t);
	}
}
