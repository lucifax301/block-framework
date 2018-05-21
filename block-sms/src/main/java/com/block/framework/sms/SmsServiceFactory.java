package com.block.framework.sms;

import java.util.HashMap;
import java.util.Map;

import com.block.framework.common.service.ServiceFactory;

public class SmsServiceFactory implements ServiceFactory<SmsService>{

	private Map<String,SmsService> services = new HashMap<String,SmsService>();

	private String defaultName;
	
	
	
	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	
	
	public Map<String, SmsService> getServices() {
		return services;
	}

	public void setServices(Map<String, SmsService> services) {
		this.services = services;
	}
	
	@Override
	public SmsService getService(String name)
    {
		return services.get(name);
    }
	@Override
	public SmsService getService(){
		return services.get(defaultName);
	}
}
