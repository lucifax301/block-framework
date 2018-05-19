package com.block.framework.pay;

import java.util.concurrent.ConcurrentHashMap;

public class PurposeHandlerFactory {

	private static ConcurrentHashMap<PurposeType,IPayPurpose> purposeHandler = new ConcurrentHashMap<PurposeType,IPayPurpose>();
	
	public static void addHandler(PurposeType purposeType,IPayPurpose handler){
		purposeHandler.putIfAbsent(purposeType, handler);
	}
	
	public static IPayPurpose getHandler(PurposeType purposeType){
		return purposeHandler.get(purposeType);
	}
}
