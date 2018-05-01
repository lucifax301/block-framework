package com.block.framework.common.util;

import com.block.framework.common.model.BuModel;
import com.github.pagehelper.PageHelper;

public class PageUtil {
	
	/**
	 * 请求分页DTO入参继承该对象
	 * PageNo = -1时 不分页
	 * @param params
	 */
	public static void startPage(BuModel model) {
		if (model.getPageNo() == -1) {
			return;
		}
		PageHelper.startPage(model.getPageNo(),model.getPageSize()); 
	}
}
