package com.block.framework.core.dao;

import java.util.List;

public interface BlockDao<T> {

	int insert(T model);
	
	int update(T model);
	
	List<T> list(T model);
	
	int delete(T model);
	
	T getById(T model);
	
	int deleteById(T model);
	
	int count(T model);
}
