package com.block.framework.core;

import org.springframework.stereotype.Service;

import com.block.framework.common.util.ApplicationContextUtil;

@Service
public class ServiceMediatorImpl implements ServiceMediator {

	@Override
	public <T> T getService(Class<T> cls) {
		return getBean(cls);
	}

	private <T> T getBean(Class<T> cls){
		
		return ApplicationContextUtil.getBean(cls);
	}
}
