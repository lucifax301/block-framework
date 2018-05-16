package com.block.framework.redis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.block.framework.common.util.StringUtil;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class ClusterRedisClient implements RedisClient {

	@Autowired
    private JedisCluster jedisCluster;
	@Resource(name="keySerializer")
    private RedisSerializer keySerializer;
    @Resource(name="valueSerializer")
    private RedisSerializer valueSerializer;
	
	@Override
	public <T> void set(byte[] key, byte[] value, int liveSecond) {
		jedisCluster.set(key, value);
		if(liveSecond>0){
			jedisCluster.expire(key, liveSecond);
		}
	}

	@Override
	public <T> void set(String key, T value, int liveSecond) {
		if(StringUtil.isNullString(key)|| value==null){
    		return;
    	}
    	byte[] byteKey=keySerializer.serialize(key);
    	byte[] byteValue=valueSerializer.serialize(value);
    	jedisCluster.set(byteKey, byteValue);
		if(liveSecond>0){
			jedisCluster.expire(byteKey, liveSecond);
		}

	}

	@Override
	public <T> void set(String key, T value) {
		set(key,value,0);

	}

	//1 if the key was set 0 if the key was not set
	@Override
	public <T> Boolean setNX(String key, T value, int liveSecond) {
		byte[] byteKey=keySerializer.serialize(key);
    	byte[] byteValue=valueSerializer.serialize(value);
		long r = jedisCluster.setnx(byteKey, byteValue);
		if(r==1&&liveSecond>0){
			jedisCluster.expire(byteKey, liveSecond);
		}
		return r==1?true:false;
	}

	@Override
	public Boolean setNX(byte[] key, byte[] value, int liveSecond) {
		long r = jedisCluster.setnx(key, value);
		if(r==1&&liveSecond>0){
			jedisCluster.expire(key, liveSecond);
		}
		return r==1?true:false;
	}

	@Override
	public <T> Boolean setNX(String key, T value) {
		return setNX(key,value,0);
	}

	@Override
	public <T> T get(String key) {
		if(StringUtil.isNullString(key)) {
	    	   return null;
	       }
	       byte[] byteKey=keySerializer.serialize(key);
	       byte[] value=jedisCluster.get(byteKey);
	       
	       if(value!=null) {
	    	   return (T)valueSerializer.deserialize(value);
	       }
	       return null;
	}

	@Override
	public byte[] get(byte[] key) {
		return jedisCluster.get(key);
	}

	@Override
	public <T> void mset(Map<String, T> map, int liveSecond) {
		if(map==null||map.isEmpty()){
    		return;
    	}
    	Set<String> keySet=map.keySet();
    	byte[][] keysvalues=new byte[keySet.size()*2][];
    	Iterator<String> it=keySet.iterator();
    	int i=0;
    	while (it.hasNext()){
    		String key=it.next();
    		keysvalues[i++]=keySerializer.serialize(key);
    		keysvalues[i++]=valueSerializer.serialize(map.get(key));
    	}
    	jedisCluster.mset(keysvalues);
	}

	@Override
	public <T> List<T> mget(List<String> keys) {
		if(keys==null || keys.isEmpty()){
    		return null;
    	}
		List<T> list=new ArrayList<T>();
    	byte[][] byteKey=new byte[keys.size()][];
    	for(int i=0;i<keys.size();i++){
    		byteKey[i]=keySerializer.serialize(keys.get(i));
    	}
    	List<byte[]> byteValue=jedisCluster.mget(byteKey);
    	if(byteValue==null || byteValue.isEmpty()){
    		return null;
    	}
    	for(int i=0;i<byteValue.size();i++){
    		list.add((T)(valueSerializer.deserialize(byteValue.get(i))));
    	}
    	return list;
	}

	@Override
	public Set<String> keys(String pattern) {
		Set<String> r = new HashSet<>();
		for(JedisPool jedisPool: jedisCluster.getClusterNodes().values()){
			r.addAll(jedisPool.getResource().keys(pattern));
		}
		return r;
		
	}

	@Override
	public void del(String key) {
		if(StringUtil.isNullString(key)){
    		return;
    	}
    	byte[] byteKey=keySerializer.serialize(key);
    	jedisCluster.del(byteKey);
	}

	@Override
	public boolean isExist(String key, String value) {
		Object redisValue=this.get(key);
    	if(redisValue!=null && redisValue.equals(value)){
    		return true;
    	}
    	return false;
	}

	@Override
	public boolean isExist(String key) {
		if(StringUtil.isNullString(key)){
    		return false;
    	}
    	@SuppressWarnings("unchecked")
		byte[] byteKey=keySerializer.serialize(key);
    	return jedisCluster.exists(byteKey);
	}

	@Override
	public void zAdd(String key, String member, double score) {
		jedisCluster.zadd(key, score, member);
	}

	@Override
	public void zRemove(String key, String member) {
		jedisCluster.zrem(key, member);

	}

	@Override
	public <T> List<T> zGet(String key, int offset, int length) {
		byte[] byteKey=keySerializer.serialize(key);
		Set<byte[]> set = jedisCluster.zrange(byteKey, offset, offset+length);
		List<T> list=new ArrayList();
		java.util.Iterator<byte[]> it= set.iterator();
		while(it.hasNext()){
			T c=(T)valueSerializer.deserialize(it.next());
		}
		return list;
	}

}
