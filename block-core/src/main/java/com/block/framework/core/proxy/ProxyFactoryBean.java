package com.block.framework.core.proxy;

import org.springframework.beans.factory.FactoryBean;

public class ProxyFactoryBean<T> implements FactoryBean<T> {

	private Class<T> serviceInterface;

	@Override
	public T getObject() throws Exception {
		return (T)ProxyPool.get(serviceInterface);
//		ProxyInvocationHandler invocationHandler = new ProxyInvocationHandler(serviceInterface);
//		return (T) invocationHandler.getProxy();  
	}

	@Override
	public Class<?> getObjectType() {
		return this.serviceInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	

}
