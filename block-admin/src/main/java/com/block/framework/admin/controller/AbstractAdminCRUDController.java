package com.block.framework.admin.controller;

import javax.servlet.http.HttpServletRequest;

import com.block.framework.admin.model.AdminUser;
import com.block.framework.core.constant.CoreConstants;
import com.block.framework.web.controller.AbstractCRUDController;

public abstract class AbstractAdminCRUDController<T, S> extends AbstractCRUDController<T, S> {

	
	protected AdminUser getUser(HttpServletRequest request) {
		AdminUser user = (AdminUser) request.getSession().getAttribute(CoreConstants.USER_SESSION);
		return user;
	}
}
