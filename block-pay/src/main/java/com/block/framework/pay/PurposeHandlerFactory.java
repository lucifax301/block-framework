package com.block.framework.pay;

import java.util.concurrent.ConcurrentHashMap;

public class PurposeHandlerFactory {

	private static ConcurrentHashMap<Integer,IPayPurpose> purposeHandler = new ConcurrentHashMap<Integer,IPayPurpose>();
	
	public static void addHandler(PurposeType purposeType,IPayPurpose handler){
		purposeHandler.putIfAbsent(purposeType.getType(), handler);
	}
	
	public static IPayPurpose getHandler(PurposeType purposeType){
		return purposeHandler.get(purposeType.getType());
	}
}
