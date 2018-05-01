package com.block.framework.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法描述注解，用在requestMapping方法上，当发生异常拦截的时候使用注解里的信息填充异常
 * @author devil
 *
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionDescription {
	String description() default "";
	String error();
}
