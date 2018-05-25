package com.block.framework.core.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import com.block.framework.common.exception.ConfigException;

public class HttpHandlerService {

	private static ConcurrentHashMap<String,HttpHandler> handlers = new ConcurrentHashMap<String,HttpHandler>(64);
	
	private static List<String> paths = new ArrayList();
	
	public static void addHandler(String path,HttpHandler handler){
		if(handlers.contains(path)){
			throw new ConfigException("restful path:"+path+" exist.");
		}
		handlers.put(path, handler);
		paths.add(path);
		Collections.sort(paths);
	}
	
	public static HttpHandler resolve(HttpServletRequest request,HttpResolver resolver){
		for(String path:paths){
			if(resolver.resolve(path,request)){
				return handlers.get(path);
			}
		}
		return null;
	}
}
