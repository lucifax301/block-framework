package com.block.framework.core;

public interface ServiceMediator {

	<T> T getService(Class<T> cls);
}
