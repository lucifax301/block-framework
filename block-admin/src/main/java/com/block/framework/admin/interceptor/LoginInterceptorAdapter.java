package com.block.framework.admin.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.block.framework.admin.model.AdminUser;
import com.block.framework.admin.util.DevProperties;
import com.block.framework.common.model.ResultBean;
import com.block.framework.common.util.GsonUtil;
import com.block.framework.core.constant.CoreConstants;
import com.block.framework.core.context.RequestContext;

/*
 * mvc 权限拦截器
 */
public class LoginInterceptorAdapter extends HandlerInterceptorAdapter {

	//protected final Logger access = Logger.getLogger(this.getClass());
	
	private static Logger logger = LoggerFactory.getLogger(LoginInterceptorAdapter.class);
	
	@Resource(name="devProperties")
	private DevProperties devProperties;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("#########login preHandle");
		String url = request.getRequestURL().toString();
		
		HttpSession session = request.getSession();
		
		if(devProperties.getDev().equals("0")){
			logger.warn("###############dev model");
			AdminUser user0=new AdminUser();
			user0.setId(22);
			user0.setAccount("admin");
			user0.setRealName("系统管理员");
			
			user0.setRoleId(1);
			
			session.setAttribute(CoreConstants.USER_SESSION, user0); 
			RequestContext.putValue(CoreConstants.USER_SESSION, user0);
        	return true;
		}
		
		if (session.getAttribute(CoreConstants.USER_SESSION) == null) {
		    printJson(response, "用户请登录!",null);
			return false;
		} 
		
		AdminUser user = (AdminUser) session.getAttribute(CoreConstants.USER_SESSION);
		
		RequestContext.putValue(CoreConstants.USER_SESSION, user);
		
		return true;

	}
	
	@Override
	public void postHandle(    
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)    
            throws Exception {    
		logger.debug("#########login postHandle");
		//System.out.println("postHandle");
    }   
	
	@Override
	public void afterCompletion(    
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)    
            throws Exception { 
		logger.debug("#########login afterCompletion");
		//System.out.println("afterCompletion");
    }  
	
	private void printJson(HttpServletResponse response,String msg, Exception ex) {
		ResultBean rb = new ResultBean(msg);
		response.setContentType("application/json"); 
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;

		if(ex!=null){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			rb.setStack(sw.toString());
		}
		try {
			out = response.getWriter();
			out.print(GsonUtil.serialNulls(rb));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
	}
}
