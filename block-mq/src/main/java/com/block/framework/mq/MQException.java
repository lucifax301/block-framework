package com.block.framework.mq;

import com.block.framework.common.exception.BlockException;

public class MQException extends BlockException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4493749147657239950L;

	public MQException() {
		super();
	}

	public MQException(String arg0) {
		super(arg0);
	}
	
	public MQException(String arg0,Throwable t){
		super(arg0,t);
	}
	
	public MQException(Throwable t){
		super(t);
	}
}
