package com.block.framework.core;

import java.util.List;

import com.block.framework.common.model.ResultBean;
import com.block.framework.core.context.RequestContext;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.BlockCacheObjectFactory;

public class CommonService {

	public String getMethodName(){
		return Thread.currentThread().getStackTrace()[1].getMethodName();
	}
	
	public <T> T getContextValue(String key){
		return RequestContext.<T>getValue(key);
	}
	
	public <T> PageInfo<T> buildPageInfo(List<T> list){
		return new PageInfo<T>(list);
	}
	
	public <T> ResultBean buildListResult(List<T> list){
		ResultBean rb = new ResultBean();
		rb.setResult(new PageInfo<T>(list));
		return rb;
	}
	
	public <T> ResultBean buildResult(T obj){
		ResultBean rb = new ResultBean();
		rb.setResult(obj);
		return rb;
	}
	
	public ResultBean getResult(){
		return BlockCacheObjectFactory.<ResultBean>getObject(ResultBean.class);
	}
}
