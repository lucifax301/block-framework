package com.block.framework.pay;

import java.util.concurrent.ConcurrentHashMap;

public class PurposeHandlerFactory {

	private static ConcurrentHashMap<Integer,IPayPurpose> purposeHandler = new ConcurrentHashMap<Integer,IPayPurpose>();
	
	private static ConcurrentHashMap<Integer,PurposeType> purposeTypes = new ConcurrentHashMap<Integer,PurposeType>();
	
	public static void addHandler(PurposeType purposeType,IPayPurpose handler){
		purposeHandler.putIfAbsent(purposeType.getType(), handler);
		purposeTypes.putIfAbsent(purposeType.getType(), purposeType);
	}
	
	public static IPayPurpose getHandler(PurposeType purposeType){
		return purposeHandler.get(purposeType.getType());
	}
	
	public static PurposeType getPurposeType(int type){
		return purposeTypes.get(type);
	}
}
