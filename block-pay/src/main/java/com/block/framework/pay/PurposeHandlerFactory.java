package com.block.framework.pay;

import java.util.HashMap;
import java.util.Map;

public class PurposeHandlerFactory {

	private static Map<PurposeType,IPayPurpose> purposeHandler = new HashMap<PurposeType,IPayPurpose>();
	
	public static void addHandler(PurposeType purposeType,IPayPurpose handler){
		purposeHandler.putIfAbsent(purposeType, handler);
	}
	
	public static IPayPurpose getHandler(PurposeType purposeType){
		return purposeHandler.get(purposeType);
	}
}
