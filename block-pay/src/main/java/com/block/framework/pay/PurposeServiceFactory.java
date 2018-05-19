package com.block.framework.pay;

import java.util.concurrent.ConcurrentHashMap;

public class PurposeServiceFactory {

	private static ConcurrentHashMap<PurposeType,IPayPurposeService> purposeHandler = new ConcurrentHashMap<PurposeType,IPayPurposeService>();
	
	public static void addHandler(PurposeType purposeType,IPayPurposeService handler){
		purposeHandler.putIfAbsent(purposeType, handler);
	}
	
	public static IPayPurposeService getHandler(PurposeType purposeType){
		return purposeHandler.get(purposeType);
	}
}
