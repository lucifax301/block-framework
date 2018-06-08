package com.block.framework.config;

public class RpcModel {

	//携带rpc context
	public final static int MODEL_WITH_CONTEXT = 1;
	//不携带rpc context
	public final static int MODEL_WITHOUT_CONTEXT = 2;
	
	private static int model = 0;
	
	static{
		model = Integer.parseInt(System.getProperty("BLOCK.RPC.MODEL","2"));
	}
	
	public static int getModel(){
		return model;
	}
}
