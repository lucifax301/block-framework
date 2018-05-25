package com.block.framework.core.trace;

public interface TraceExtractor <T>{

	InnerTrace attachTrace(T carrier);
}
