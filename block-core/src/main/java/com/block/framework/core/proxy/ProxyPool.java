package com.block.framework.core.proxy;

import java.util.concurrent.ConcurrentHashMap;

public class ProxyPool {

	private static ConcurrentHashMap<Class<?>,Object> pool = new ConcurrentHashMap<Class<?>,Object>();
	
	public static <T> void add(Class<T> cls){
		if(!pool.containsKey(cls)){
			ProxyInvocationHandler invocationHandler = new ProxyInvocationHandler(cls);
			Object proxy = invocationHandler.getProxy();
			pool.put(cls, proxy);
		}
	}
	
	public static <T> T get(Class<T> cls){
		return (T)pool.get(cls);
	}
}
