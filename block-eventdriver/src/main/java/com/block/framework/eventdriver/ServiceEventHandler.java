package com.block.framework.eventdriver;

/**
 * 
 * @author devil
 *
 */
public class ServiceEventHandler {

	public static <R> R handle(ServiceEvent<R> event){
		BlockService serivce = ServiceEventRegistry.getService((Class<ServiceEvent>)event.getClass());
		return serivce.process(event);
	}
}
