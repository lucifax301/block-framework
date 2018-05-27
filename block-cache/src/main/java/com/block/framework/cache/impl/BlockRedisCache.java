package com.block.framework.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.cache.BlockCache;
import com.block.framework.redis.RedisClient;

public class BlockRedisCache implements BlockCache {

	@Autowired
	private RedisClient redisClient;
	
	@Override
	public <T> void set(String key, T value) {
		redisClient.set(key, value);
	}

	@Override
	public <T> T get(String key) {
		return redisClient.get(key);
	}

	@Override
	public <T> void remove(String key) {
		redisClient.del(key);
	}

}
