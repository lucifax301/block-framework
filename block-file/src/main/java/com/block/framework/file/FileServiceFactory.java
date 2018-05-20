package com.block.framework.file;

import java.util.HashMap;
import java.util.Map;


public class FileServiceFactory {

	private Map<String,FileService> services = new HashMap<String,FileService>();

	
	
	public Map<String, FileService> getPayGateways() {
		return services;
	}

	public void setPayGateways(Map<String, FileService> services) {
		this.services = services;
	}
	
	public FileService getPayAction(String name)
    {
		return services.get(name);
    }
}
