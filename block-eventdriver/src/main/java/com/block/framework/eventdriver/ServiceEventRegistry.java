package com.block.framework.eventdriver;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author devil
 *
 */
public class ServiceEventRegistry {

	private static ConcurrentHashMap<Class<ServiceEvent>, BlockService> servicePool = new ConcurrentHashMap<Class<ServiceEvent>, BlockService>();
	
	public static void register(Class<ServiceEvent> event,BlockService service){
		servicePool.putIfAbsent(event, service);
	}
	
	public static BlockService getService(Class<ServiceEvent> event){
		return servicePool.get(event);
	}
}
