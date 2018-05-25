package com.block.framework.core.trace;

public interface TraceInjector<T> {

	void inject(InnerTrace innerTrace,T carrier);
}
