package com.block.framework.file;

import java.util.HashMap;
import java.util.Map;

import com.block.framework.common.service.ServiceFactory;


public class FileServiceFactory implements ServiceFactory<FileService>{

	private Map<String,FileService> services = new HashMap<String,FileService>();
	
	private String defaultName;

	public Map<String, FileService> getServices() {
		return services;
	}


	public void setServices(Map<String, FileService> services) {
		this.services = services;
	}


	@Override
	public FileService getService(String name) {
		return services.get(name);
	}


	@Override
	public FileService getService() {
		return getService(defaultName);
	}
	
	
}
