package com.block.framework.pay;

import java.util.HashMap;
import java.util.Map;

public class PayFactory {

	private Map<String,PayAction> payGateways = new HashMap<String,PayAction>();

	static {
		PayConfig.init();
	}
	
	public Map<String, PayAction> getPayGateways() {
		return payGateways;
	}

	public void setPayGateways(Map<String, PayAction> payGateways) {
		this.payGateways = payGateways;
	}
	
	public PayAction getPayAction(String payWay)
    {
		return payGateways.get(payWay);
    }
}
