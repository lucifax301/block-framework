package com.block.framework.metric;

public interface Guage<T> extends Metric{

	T getValue();
}
