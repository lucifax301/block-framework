package com.block.framework.core.trace;

public class TraceConfig {

	private static boolean enable;
	
	static{
		enable = Boolean.valueOf(System.getProperty("trace.enable", "false"));
	}
	
	public static boolean enableTrace(){
		return enable;
	}
}
