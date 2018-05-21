package com.block.framework.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.block.framework.common.model.ResultBean;

public abstract class AbstractCRUDController<T,S> {

	abstract ResultBean add(T model,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	abstract ResultBean update(T model,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	abstract ResultBean delete(T model,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	abstract ResultBean list(T model,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	abstract ResultBean get(T model,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public S getService(){
		return null;
	}
}
