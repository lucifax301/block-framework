package com.block.framework.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class CommonRedisClient implements RedisClient {

	@Resource(name="redisTemplate")
    protected RedisTemplate<Serializable, Serializable> redisTemplate;

	
    public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
		return redisTemplate;
	}
	public void setRedisTemplate(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	public <T> void set(final byte[] key,final byte[] value, final int liveSecond) {
		redisTemplate.execute(new RedisCallback<T>()
		        {
		            @SuppressWarnings("unchecked")
					@Override
		            public T doInRedis(RedisConnection connection) throws DataAccessException
		            {
		                connection.set(key,value);
		                if (liveSecond > 0) {
		                    connection.expire(key, liveSecond);
		                }
		                
		                return null;
		            }
		        });

	}

	@Override
	public <T> void set(final String key, final T value,final int liveSecond) {
		redisTemplate.execute(new RedisCallback<T>()
		        {
		            @SuppressWarnings("unchecked")
					@Override
		            public T doInRedis(RedisConnection connection) throws DataAccessException
		            {
		                connection.set(redisTemplate.getStringSerializer().serialize(key),
		                        ((RedisSerializer<T>)redisTemplate.getValueSerializer()).serialize(value));
		                if (liveSecond > 0) {
		                    connection.expire(redisTemplate.getStringSerializer().serialize(key), liveSecond);
		                }
		                
		                return null;
		            }
		        });

	}

	@Override
	public <T> void set(final String key,final T value) {
		set(key,value,0);

	}

	@Override
	public <T> Boolean setNX(final String key,final T value,final int liveSecond) {
		return redisTemplate.execute(new RedisCallback<Boolean>()
		        {
		            @SuppressWarnings("unchecked")
					@Override
		            public Boolean doInRedis(RedisConnection connection) throws DataAccessException
		            {
		                Boolean r =  connection.setNX(redisTemplate.getStringSerializer().serialize(key),
		                        ((RedisSerializer<T>)redisTemplate.getValueSerializer()).serialize(value));
		                if (liveSecond > 0) {
		                    connection.expire(redisTemplate.getStringSerializer().serialize(key), liveSecond);
		                }
		                
		                return r;
		            }
		        });
	}

	@Override
	public Boolean setNX(final byte[] key, final byte[] value, final int liveSecond) {
		return redisTemplate.execute(new RedisCallback<Boolean>()
		        {
		            
					@Override
		            public Boolean doInRedis(RedisConnection connection) throws DataAccessException
		            {
		                Boolean r =  connection.setNX(key, value);
		                if (liveSecond > 0) {
		                    connection.expire(key, liveSecond);
		                }
		                
		                return r;
		            }
		        });
	}

	@Override
	public <T> Boolean setNX(final String key,final T value) {
		return redisTemplate.execute(new RedisCallback<Boolean>()
		        {
		            @SuppressWarnings("unchecked")
					@Override
		            public Boolean doInRedis(RedisConnection connection) throws DataAccessException
		            {
		                Boolean r =  connection.setNX(redisTemplate.getStringSerializer().serialize(key),
		                        ((RedisSerializer<T>)redisTemplate.getValueSerializer()).serialize(value));
		                return r;
		            }
		        });
	}

	@Override
	public <T> T get(final String key) {
		return redisTemplate.execute(new RedisCallback<T>()
		        {
		            @SuppressWarnings("unchecked")
					@Override
		            public T doInRedis(RedisConnection connection) throws DataAccessException
		            {
		                byte[] keys = redisTemplate.getStringSerializer().serialize(key);
		                byte[] value = connection.get(keys);
		                T r=null;
		                if (value!=null)
		                {
		                	r=((RedisSerializer<T>)redisTemplate.getValueSerializer()).deserialize(value);
		                }
		                return r;
		            }
		        });
	}

	@Override
	public byte[] get(final byte[] key) {
		return redisTemplate.execute(new RedisCallback<byte[]>()
		        {
		            @SuppressWarnings("unchecked")
					@Override
		            public byte[] doInRedis(RedisConnection connection) throws DataAccessException
		            {
		                byte[] keys = key;
		                byte[] value = connection.get(keys);
		                
		                return value;
		            }
		        });
	}

	@Override
	public <T> void mset(final Map<String, T> map, int liveSecond) {
		redisTemplate.execute(new RedisCallback<T>()
		        {
		            @SuppressWarnings("unchecked")
					@Override
		            public T doInRedis(RedisConnection connection) throws DataAccessException
		            {
		            	if(null == map || map.size()==0){
		            		return null;
		            	}
		            	Map<byte[],byte[]> data = new HashMap<byte[], byte[]>();
		            	Iterator<Entry<String, T>> it = map.entrySet().iterator();
		            	while(it.hasNext()){
		            		Entry<String, T> entry = it.next();
		            		String key = entry.getKey();
		            		T value = entry.getValue();
		            		data.put(redisTemplate.getStringSerializer().serialize(key), 
		            				((RedisSerializer<T>)redisTemplate.getValueSerializer()).serialize(value));
		            	}
		            	connection.mSet(data);
		            	
		                return null;
		            }
		        });

	}

	@Override
	public <T> List<T> mget(final List<String> keys) {
		return redisTemplate.execute(new RedisCallback<List<T>>()
    			{
		    		@SuppressWarnings("unchecked")
		    		@Override
		    		public List<T> doInRedis(RedisConnection connection) throws DataAccessException
		    		{
		    			byte[][] kk = new byte[keys.size()][];
		    			for(int i=0;i<keys.size();i++){
		    				String k = keys.get(i);
		    				byte[] ks = redisTemplate.getStringSerializer().serialize(k);
		    				kk[i]= ks;
		    			}
		    			List<byte[]> value = connection.mGet(kk);
		    			List<T> r=new ArrayList<T>();
		    			
		    			if (value!=null && value.size()>0)
		    			{
		    				for(int i=0;i<value.size();i++){
		    					T a=((RedisSerializer<T>)redisTemplate.getValueSerializer()).deserialize(value.get(i));
		    					r.add(a);
		    				}
		    			}
		    			return r;
		    		}
    			});
	}

	@Override
	public Set<String> keys(final String pattern) {
		return redisTemplate.execute(new RedisCallback<Set<String>>()
		        {
		            @SuppressWarnings("unchecked")
					@Override
		            public Set<String> doInRedis(RedisConnection connection) throws DataAccessException
		            {
		                byte[] patterns = redisTemplate.getStringSerializer().serialize(pattern);
		                Set<byte[]> values = connection.keys(patterns);
		                Set<String> r = new HashSet<>();
		                if (values!=null)
		                {
		                	for (byte[] value : values)
		                		r.add(new String(value));
		                }
		                return r;
		            }
		        });
	}

	@Override
	public void del(final String key) {
		redisTemplate.execute(new RedisCallback<Object>()
		        {
		            @Override
		            public Object doInRedis(RedisConnection connection) throws DataAccessException
		            {
		                byte[] keys = redisTemplate.getStringSerializer().serialize(key);
		                if (connection.exists(keys))
		                {
		                    connection.del(keys);
		                }
		                
		                return null;
		            }
		        });
	}

	@Override
	public boolean isExist(final String key, final String value) {
		String t = get(key);
        if (t != null && t.equals(value))
        {
            return true;
        }
        return false;
	}

	@Override
	public boolean isExist(final String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keys = redisTemplate.getStringSerializer()
						.serialize(key);
				if (connection.exists(keys)) {
					return "1";
				}
				return "0";
			}
		});
		if ("1".equals(result)) {
			return true;
		}
		return false;
	}

	@Override
	public void zAdd(final String key, final String member, final double score) {
		redisTemplate.execute(new RedisCallback<String>()
		        {
		            @SuppressWarnings("unchecked")
					@Override
		            public String doInRedis(RedisConnection connection) throws DataAccessException
		            {
		            	connection.zAdd(redisTemplate.getStringSerializer().serialize(key), score, ((RedisSerializer<String>)redisTemplate.getValueSerializer()).serialize(member));
		                
		                return null;
		            }
		        });
	}

	@Override
	public void zRemove(final String key, final String member) {
		redisTemplate.execute(new RedisCallback<String>()
		        {
		            @SuppressWarnings("unchecked")
					@Override
		            public String doInRedis(RedisConnection connection) throws DataAccessException
		            {
		            	connection.zRem(redisTemplate.getStringSerializer().serialize(key), ((RedisSerializer<String>)redisTemplate.getValueSerializer()).serialize(member));
		            	
		                return null;
		            }
		        });
	}

	@Override
	public <T> List<T> zGet(final String key, final int offset, final int length) {
		return redisTemplate.execute(new RedisCallback<List<T>>()
    			{
		    		@SuppressWarnings("unchecked")
		    		@Override
		    		public List<T> doInRedis(RedisConnection connection) throws DataAccessException
		    		{
		    			Set<byte[]> set= connection.zRange(redisTemplate.getStringSerializer().serialize(key), offset, length+offset);
		    			List<T> list=new ArrayList();
		    			java.util.Iterator<byte[]> it= set.iterator();
		    			while(it.hasNext()){
		    				T c=((RedisSerializer<T>)redisTemplate.getValueSerializer()).deserialize(it.next());
		    			}
		    			return list;
		    		}
    			});
	}

}
