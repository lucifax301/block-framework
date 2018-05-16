package com.block.framework.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisClient {

	<T> void set(byte[] key, byte[] value,int liveSecond);
	
	<T> void set(String key, T value,int liveSecond);
	
	<T> void set(String key, T value);
	
	<T> Boolean setNX(String key, T value,int liveSecond);
	
	Boolean setNX(byte[] key, byte[] value,int liveSecond);
	
	<T> Boolean setNX(String key, T value);
	
	<T> T get(String key);
	
	byte[] get(byte[] key);
	
	public <T> void mset(Map<String,T> map,int liveSecond);
	
	public <T> List<T> mget(List<String> keys);
	
	Set<String> keys(String pattern);
	
	void del(String key);
	
	boolean isExist(String key, String value);
	
	boolean isExist(String key);
	
	<T> void zAdd(String key,String member,double score);
	
	<T> void zRemove(String key,String member);
	
	<T> List<T> zGet(String key,int offset,int length);
}
