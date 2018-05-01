package com.google.common.collect;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.block.framework.common.exception.BlockException;
import com.google.common.base.Function;

/**
 * 
 * @author devil
 *
 */
public class BlockCacheObjectFactory {

	@SuppressWarnings("deprecation")
	private static Map<Class<?>,Object> pool=new MapMaker().expireAfterAccess(10, TimeUnit.MINUTES) .makeComputingMap(new Function<Class<?>,Object>(){

		@Override
		public Object apply(Class<?> clz) {
			try{
				return clz.newInstance();
			}catch(Exception ex){
				throw new BlockException(ex);
			}
		}
	});
	
	public static Object getObject(Class<?> clz){
		return pool.get(clz);
	}
}
