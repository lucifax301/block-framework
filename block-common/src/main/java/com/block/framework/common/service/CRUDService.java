package com.block.framework.common.service;

import java.util.List;

import com.block.framework.common.model.ResultBean;
import com.github.pagehelper.PageInfo;

public interface CRUDService<T> {

	ResultBean add(T model);
	
	ResultBean update(T model);
	
	ResultBean delete(T model);
	
	ResultBean get(T model);
	
	PageInfo<T> listPage(T model);
	
	List<T> list(T model);
	
	T getOne(T model);
}
