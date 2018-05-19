package com.block.framework.cache;

import java.util.concurrent.ConcurrentHashMap;

import com.block.framework.common.exception.ConfigException;

public class BlockCacheFactory {

	private String defaultCache = null;
	
	private ConcurrentHashMap<String,BlockCache> pools = new ConcurrentHashMap<String,BlockCache>();

	public ConcurrentHashMap<String, BlockCache> getPools() {
		return pools;
	}

	public void setPools(ConcurrentHashMap<String, BlockCache> pools) {
		this.pools = pools;
	}
	
	
	
	public String getDefaultCache() {
		return defaultCache;
	}

	public void setDefaultCache(String defaultCache) {
		this.defaultCache = defaultCache;
	}

	public BlockCache getCache(String name){
		return pools.get(name);
	}
	
	public BlockCache getCache(){
		if(defaultCache==null)
			throw new ConfigException("default cache not set");
		return pools.get(defaultCache);
	}
}
