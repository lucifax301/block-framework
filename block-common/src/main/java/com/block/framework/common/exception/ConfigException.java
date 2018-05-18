package com.block.framework.common.exception;

public class ConfigException extends BlockException {

	public ConfigException() {
		super();
	}

	public ConfigException(String arg0) {
		super(arg0);
	}
	
	public ConfigException(String arg0,Throwable t){
		super(arg0,t);
	}
	
	public ConfigException(Throwable t){
		super(t);
	}
}
