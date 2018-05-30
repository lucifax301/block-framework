package com.block.framework.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.block.framework.common.model.ResultBean;

public class AbstractLogoutController extends AdminController {

	public ResultBean logout(HttpServletRequest request) {
		return doLogout(request);
	}
	
	private ResultBean doLogout(HttpServletRequest request){
		ResultBean rb = new ResultBean();
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
			rb.setMsg("用戶注销成功!");
		}
		return rb;
	}
}
