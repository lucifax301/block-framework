package com.block.framework.common.session;

import com.block.framework.common.session.impl.RedisSession;

public class SessionFactory {

	private static String DEFAULT_KEY = "redis";
	
	public static BlockSession getSessionImpl(){
		return getSessionImpl(DEFAULT_KEY);
	}
	
	public static BlockSession getSessionImpl(String key){
		return new RedisSession();
	}
}
