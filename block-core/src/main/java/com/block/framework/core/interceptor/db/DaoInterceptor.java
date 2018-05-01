package com.block.framework.core.interceptor.db;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.block.framework.common.annotation.DBRoute;
import com.block.framework.common.util.ThreadTruck;
import com.block.framework.core.constant.CoreConstants;

/**
 * 拦截dao接口，如果dao有DBRoute注解路由到MRD数据库，就注入到线程里，最后释放
 * @author Administrator
 *
 */
public class DaoInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		//Annotation[] annotations = invocation.getMethod() .getClass().getAnnotations();
		Class<?> cls = invocation.getMethod().getDeclaringClass();
		DBRoute route = (DBRoute)cls.getAnnotation(DBRoute.class);
		
		try{
			if(route!=null){
				ThreadTruck.put(CoreConstants.ROUTE_DB, route.value());
			}
			return invocation.proceed();
		}finally{
			if(route!=null){
				ThreadTruck.remove(CoreConstants.ROUTE_DB);
			}
		}
	}

}
