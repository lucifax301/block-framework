package com.block.framework.pay;

import java.util.HashMap;
import java.util.Map;

public class PurposeServiceFactory {

	private static Map<PurposeType,IPayPurposeService> purposeHandler = new HashMap<PurposeType,IPayPurposeService>();
	
	public static void addHandler(PurposeType purposeType,IPayPurposeService handler){
		purposeHandler.putIfAbsent(purposeType, handler);
	}
	
	public static IPayPurposeService getHandler(PurposeType purposeType){
		return purposeHandler.get(purposeType);
	}
}
