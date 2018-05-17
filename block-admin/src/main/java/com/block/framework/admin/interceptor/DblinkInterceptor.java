package com.block.framework.admin.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.block.framework.admin.model.AdminUser;
import com.block.framework.common.annotation.RequestAction;
import com.block.framework.common.model.BaseModel;
import com.block.framework.common.model.BuModel;
import com.block.framework.core.constant.CoreConstants;

/**
 * controller层获取用户的session信息 ,并把session中数据库信息设置到BaseModel中。
 * @author lilixc
 */
@Aspect
@Component
public class DblinkInterceptor {

	//对spring的mapping注解做aop拦截
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerAspect() {
	}

	@Before("controllerAspect()")
	public void before(JoinPoint joinPoint) {
		System.out.println("dblink已经记录下操作日志@Before 方法执行前");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Object [] args = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  
        Method method = methodSignature.getMethod();
        //Class cls = method.getDeclaringClass();
        RequestAction requestAction = (RequestAction)method.getAnnotation(RequestAction.class);
		for (Object o : args) {
			if (o instanceof BuModel) {
				AdminUser user = (AdminUser)session.getAttribute(CoreConstants.USER_SESSION);
				
				if (user != null) {
					((BuModel) o).setDblink(user.getDblink());
					
					
					if(requestAction!=null){
						if(requestAction.type()==RequestAction.RequestActionType.ADD){
							((BaseModel) o).setCuid(user.getId());
						}else if(requestAction.type()==RequestAction.RequestActionType.UPDATE){
							((BaseModel) o).setMuid(user.getId());
						}
					}
				}
			}
		}
	}
	
	@Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("dblink已经记录下操作日志@Around 方法执行前");
        return pjp.proceed();
       
    }

    @After("controllerAspect()")
    public void after() {
        System.out.println("dblink已经记录下操作日志@After 方法执行后");
    }
}
