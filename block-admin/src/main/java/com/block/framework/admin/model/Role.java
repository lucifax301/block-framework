package com.block.framework.admin.model;

import java.util.Date;
import java.util.List;

import com.block.framework.common.model.BuModel;

public class Role extends BuModel {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String rolename;
	//1--启用，0--停用
	private Integer enable;
	
	private String remark;
	
	private String creator;
	
	private String updator;
	
	private Integer privilegeCount;
	
	private Integer userCount;
	
	private List<RolePrivilege> rolePrivileges;
	
	private List<Privilege> privileges;
	
	private String privilegestr;
	
	private Integer isAdmin;
	
	private Integer isGroup;
	
	private Integer level;
	
	private String ids;
	
	private Integer pid;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	

	public Integer getPrivilegeCount() {
		return privilegeCount;
	}

	public void setPrivilegeCount(Integer privilegeCount) {
		this.privilegeCount = privilegeCount;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public List<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}

	public void setRolePrivileges(List<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public String getPrivilegestr() {
		return privilegestr;
	}

	public void setPrivilegestr(String privilegestr) {
		this.privilegestr = privilegestr;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Integer isGroup) {
		this.isGroup = isGroup;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
}
