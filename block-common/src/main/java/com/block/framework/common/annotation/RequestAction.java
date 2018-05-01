package com.block.framework.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * requestMapping 方法上使用的注解，描述此http业务请求类型
 * @author devil
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestAction {

	RequestActionType type() ;
	
	enum RequestActionType{
		ADD,UPDATE,DELETE
	}
}
