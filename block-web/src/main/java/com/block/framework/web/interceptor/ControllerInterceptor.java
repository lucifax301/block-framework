package com.block.framework.web.interceptor;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.block.framework.common.annotation.ActionDescription;
import com.block.framework.common.exception.BlockException;

/**
 * @author devil
 * 去除注解，不让自动扫包加载
 */
//@Aspect
//@Component
public class ControllerInterceptor {

	//对spring的mapping注解做aop拦截
	//@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerAspect() {
	}

	//@Before("controllerAspect()")
	public void before(JoinPoint joinPoint) {
		System.out.println("controllerAspect 已经记录下操作日志@Before 方法执行前");
		
	}
	
	//@Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("controllerAspect 已经记录下操作日志@Around 方法执行前");
		 MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
		 Method method = methodSignature.getMethod();
		
		try{
			
         return pjp.proceed();
		}catch(Throwable e){
			ActionDescription description = (ActionDescription)method.getAnnotation(ActionDescription.class);
			String error = description!=null?description.error():"";
			e.printStackTrace();
			throw new BlockException(error+":"+e.toString(),e);
			
		}
        
    }

    //@After("controllerAspect()")
    public void after() {
        System.out.println("controllerAspect 已经记录下操作日志@After 方法执行后");
    }
}