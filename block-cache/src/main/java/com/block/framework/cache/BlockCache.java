package com.block.framework.cache;

public interface BlockCache {

	<T> void set(String key,T value);
	
	<T> T get(String key);
}
