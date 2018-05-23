package com.block.framework.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.common.model.ResultBean;
import com.block.framework.core.CRUDService;
import com.block.framework.core.ServiceMediator;
import com.github.pagehelper.PageInfo;

public abstract class AbstractCRUDController<T,S> extends BaseController {

	@Autowired
	ServiceMediator serviceMediator;
	
	public abstract Class<S> getCls();
	
	ResultBean add(T model,HttpServletRequest request,HttpServletResponse response) {
		return getService(getCls()).add(model);
	}
	
	ResultBean update(T model,HttpServletRequest request,HttpServletResponse response) {
		return getService(getCls()).update(model);
	}
	
	ResultBean delete(T model,HttpServletRequest request,HttpServletResponse response){
		return getService(getCls()).delete(model);
	}
	
	PageInfo<T> listPage(T model,HttpServletRequest request,HttpServletResponse response) {
		return getService(getCls()).listPage(model);
	}
	
	List<T> list(T model,HttpServletRequest request,HttpServletResponse response) {
		return getService(getCls()).list(model);
	}
	
	T get(T model,HttpServletRequest request,HttpServletResponse response) {
		return (T)getService(getCls()).getOne(model);
	}
	
	public CRUDService getService(Class<S> cls){
		return (CRUDService)serviceMediator.getService(cls);
	}
}
