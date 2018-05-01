package com.block.framework.admin.model;

import com.block.framework.common.model.BuModel;

public class RolePrivilege extends BuModel {

	private static final long serialVersionUID = 1L;

	private int roleId;
	
	private int privilegeId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}
	
	
}
