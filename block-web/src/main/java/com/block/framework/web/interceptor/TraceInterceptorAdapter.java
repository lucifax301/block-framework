package com.block.framework.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.block.framework.core.constant.CoreConstants;
import com.block.framework.core.context.RequestContext;
import com.block.framework.core.trace.InnerTrace;
import com.block.framework.core.trace.Trace;
import com.block.framework.core.trace.TraceUtil;

/**
 * mvc最外层拦截器 ，创建和释放RequestContext
 * @author devil
 *
 */
public class TraceInterceptorAdapter extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(TraceInterceptorAdapter.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("###### create RequestContext");
		//System.out.println("###### create RequestContext");
		RequestContext rc = RequestContext.getOrCreate();
		String traceId = TraceUtil.createTraceIdString();
		rc.setTraceId(traceId);
		rc.setIp(request.getRemoteAddr());
		String name = request.getRequestURI().toString();
		InnerTrace innerTrace = Trace.createTrace(name);
		innerTrace.setServiceType("webrequest");
		innerTrace.setTraceId(traceId);
		innerTrace.setIp(rc.getIp());
		return true;
	}
	
	@Override
	public void postHandle(    
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)    
            throws Exception {    
    }   
	
	@Override
	public void afterCompletion(    
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)    
            throws Exception { 
		logger.debug("###### clear RequestContext");
		System.out.println("ex:"+ex);
		//System.out.println("###### clear RequestContext");
		Object err = RequestContext.getValue(CoreConstants.REQUEST_ERROR);
		RequestContext.set(null);
		if(err!=null){
			Trace.getCurrentTrace().setResult(1);
		}
		Trace.endTrace();
    } 
}
