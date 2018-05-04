package com.block.framework.admin.controller;

import javax.servlet.http.HttpServletRequest;

import com.block.framework.admin.model.AdminUser;
import com.block.framework.core.constant.CoreConstants;
import com.block.framework.web.controller.BaseController;

public class AdminController extends BaseController {

	/**
	 * 获取当前用户
	 * @param request
	 * @return
	 */
	protected AdminUser getUser(HttpServletRequest request) {
		AdminUser user = (AdminUser) request.getSession().getAttribute(CoreConstants.USER_SESSION);
		return user;
	}
}
