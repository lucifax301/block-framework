package com.block.framework.common.service;

public interface ServiceFactory <T>{
	
	T getService(String name);
	
	T getService();
}
