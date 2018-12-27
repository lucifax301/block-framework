package com.block.framework.metric;

public interface Gauge<T> extends Metric{

	T getValue();
}
