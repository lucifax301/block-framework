package com.block.framework.admin.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.block.framework.common.constant.ResultCode;
import com.block.framework.common.model.ResultBean;
import com.block.framework.common.util.EncryptUtil;
import com.block.framework.common.util.WebUtil;
import com.block.framework.web.controller.BaseController;

public abstract class AbstractLoginController<U> extends BaseController{

	public ResultBean login(U user, String authcode, Integer isAutoLogin, 
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultBean r = this.dologin(user, authcode, isAutoLogin, request, response);
		if(r.getCode()==ResultCode.ERRORCODE.SUCCESS){
			this.postHandle(r,user, request, response);
			return r;
		}
		return r;
	}
	
	public abstract ResultBean dologin(U user,String authcode, Integer isAutoLogin, 
			HttpServletRequest request,HttpServletResponse response);
	
	public abstract void postHandle(ResultBean r,U user,HttpServletRequest request,HttpServletResponse response);
	
	
	public void storeLoginStatus(HttpServletRequest request,
			HttpServletResponse response, String userId) throws UnsupportedEncodingException {
		
		Cookie loginCookie = new Cookie(WebUtil.COOKIE_ACCOUNT_STR, getPassword(request,request.getParameter("password"),userId));
		loginCookie.setMaxAge(WebUtil.COOKIES_EXPIRY_SECONDS);
		loginCookie.setPath(WebUtil.getContextPath(request) + "/");
		response.addCookie(loginCookie);
		
	}
	
	private String getPassword(HttpServletRequest request,String pwd,String userId) throws UnsupportedEncodingException{
		
		String ramdon = pwd.substring(pwd.length()-6, pwd.length());
		String orignPwd = request.getParameter("password");
		return EncryptUtil.SHA1(orignPwd + ramdon) + "*" + userId;
	}
	
	
}
