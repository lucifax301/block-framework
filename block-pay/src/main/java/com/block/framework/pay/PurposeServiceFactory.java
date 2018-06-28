package com.block.framework.pay;

import java.util.concurrent.ConcurrentHashMap;

public class PurposeServiceFactory {

	private static ConcurrentHashMap<Integer,IPayPurposeService> purposeHandler = new ConcurrentHashMap<Integer,IPayPurposeService>();
	
	public static void addHandler(PurposeType purposeType,IPayPurposeService handler){
		purposeHandler.putIfAbsent(purposeType.getType(), handler);
	}
	
	public static IPayPurposeService getHandler(PurposeType purposeType){
		return purposeHandler.get(purposeType.getType());
	}
}
