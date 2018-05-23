package com.block.framework.pay;

import java.util.HashMap;
import java.util.Map;

import com.block.framework.common.service.ServiceFactory;


public class PayServiceFactory implements ServiceFactory<PayService> {

	private Map<String,PayService> services = new HashMap<String,PayService>();

	private String defaultName;
	
	
	
	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	
	
	public Map<String, PayService> getServices() {
		return services;
	}

	public void setServices(Map<String, PayService> services) {
		this.services = services;
	}
	
	
	@Override
	public PayService getService(String name) {
		return services.get(name);
	}

	@Override
	public PayService getService() {
		return services.get(defaultName);
	}

}
