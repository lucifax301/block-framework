package com.block.framework.core.trace;

import org.springframework.cglib.proxy.Enhancer;

/**
 * 使用cglib修改被代理类的方法, 用TraceMethodWrapper来封装被调动方法
 * @author devil
 *
 */
public class TraceCGLibUtil {

	public static <T> T createBean(Class<T> cls){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallback(new TraceMethodWrapper());
		return (T)enhancer.create();
	}
}
