package com.block.framework.sms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.block.framework.common.service.ServiceFactory;

public class SmsServiceFactory {

	private static Map<String,SmsService> services = new HashMap<String,SmsService>();

	private static String defaultName;
//	
//	
//	
//	public String getDefaultName() {
//		return defaultName;
//	}
//
//	public void setDefaultName(String defaultName) {
//		this.defaultName = defaultName;
//	}
//
//	
//	
//	public Map<String, SmsService> getServices() {
//		return services;
//	}

//	public void setServices(Map<String, SmsService> services) {
//		this.services = services;
//	}
	
	
	public static SmsService getService(String name)
    {
		return services.get(name);
    }
	
	public static SmsService getService(){
		return services.get(defaultName);
	}
	
//	private static SmsServiceFactory factory = null;
	
//	@PostConstruct  
//	public void init() { 
//		factory = this;
//		
//	}
	
	public synchronized static void addService(String name,SmsService service){
		if(services.isEmpty()){
			defaultName=name;
		}
		services.put(name, service);
		
	}
}
