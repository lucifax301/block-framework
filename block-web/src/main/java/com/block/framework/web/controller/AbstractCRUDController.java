package com.block.framework.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.common.model.ResultBean;
import com.block.framework.common.service.CRUDService;
import com.block.framework.core.ServiceMediator;
import com.github.pagehelper.PageInfo;

public abstract class AbstractCRUDController<T,S> extends BaseController {

	@Autowired
	ServiceMediator serviceMediator;
	
	public abstract Class<S> getCls();
	
	public ResultBean add(T model,HttpServletRequest request,HttpServletResponse response) {
		return getService(getCls()).add(model);
	}
	
	public ResultBean update(T model,HttpServletRequest request,HttpServletResponse response) {
		return getService(getCls()).update(model);
	}
	
	public ResultBean delete(T model,HttpServletRequest request,HttpServletResponse response){
		return getService(getCls()).delete(model);
	}
	
	public ResultBean listPage(T model,HttpServletRequest request,HttpServletResponse response) {
		return this.buildListResult(getService(getCls()).listPage(model));
		//return getService(getCls()).listPage(model);
	}
	
	public ResultBean list(T model,HttpServletRequest request,HttpServletResponse response) {
		return this.buildListResult(getService(getCls()).list(model));
		//return getService(getCls()).listPage(model);
	}
	
	public ResultBean get(T model,HttpServletRequest request,HttpServletResponse response) {
		return this.buildResult(getService(getCls()).get(model));
		//return (T)getService(getCls()).getOne(model);
	}
	
//	List<T> list(T model,HttpServletRequest request,HttpServletResponse response) {
//		return getService(getCls()).list(model);
//	}
//	
//	T get(T model,HttpServletRequest request,HttpServletResponse response) {
//		return (T)getService(getCls()).getOne(model);
//	}
	
	public CRUDService getService(Class<S> cls){
		return (CRUDService)serviceMediator.getService(cls);
	}
}
