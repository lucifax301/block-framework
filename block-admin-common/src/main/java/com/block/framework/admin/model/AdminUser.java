package com.block.framework.admin.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.block.framework.common.model.BuModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AdminUser extends BuModel implements Serializable{
	
	private Integer id;

	private String account;

	@JsonIgnore
	private String password;

	private String realName;

	private String mobile;

	private Integer roleId;

	//0 commom 1 super
	private Integer superAdmin;
	//1--启用，0--停用
	private Integer status;
	//0 normal 1 deleted
	private Integer deleted;

	//0 normal 1 admin
	private Integer admin;
	
	private String roleName;
	
	
	//1--启用，0--停用
	private Integer enable;
	
	private String remark;
	
	private String creator;
	
	private String updator;
	
	

	private List<RolePrivilege> rolePrivileges;
	
	private String privilegestr;
		
	
	
	private static final long serialVersionUID = 1L;

	
	//1 the first time to login,0 not
	private int firstLogin;
	
	private Date firstLoginTime;
	
	private Date lastLoginTime;
	
		
	
	
	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}

	


	public String getAccount() {
		return account;
	}




	public void setAccount(String account) {
		this.account = account;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getRealName() {
		return realName;
	}




	public void setRealName(String realName) {
		this.realName = realName;
	}




	public String getMobile() {
		return mobile;
	}




	public void setMobile(String mobile) {
		this.mobile = mobile;
	}




	public Integer getRoleId() {
		return roleId;
	}




	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}




	public Integer getSuperAdmin() {
		return superAdmin;
	}




	public void setSuperAdmin(Integer superAdmin) {
		this.superAdmin = superAdmin;
	}




	public Integer getStatus() {
		return status;
	}




	public void setStatus(Integer status) {
		this.status = status;
	}




	public Integer getDeleted() {
		return deleted;
	}




	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}




	public Integer getAdmin() {
		return admin;
	}




	public void setAdmin(Integer admin) {
		this.admin = admin;
	}




	public String getRoleName() {
		return roleName;
	}




	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}




	public Integer getEnable() {
		return enable;
	}




	public void setEnable(Integer enable) {
		this.enable = enable;
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




	public List<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}




	public void setRolePrivileges(List<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}




	public String getPrivilegestr() {
		return privilegestr;
	}




	public void setPrivilegestr(String privilegestr) {
		this.privilegestr = privilegestr;
	}




	public int getFirstLogin() {
		return firstLogin;
	}




	public void setFirstLogin(int firstLogin) {
		this.firstLogin = firstLogin;
	}




	public Date getFirstLoginTime() {
		return firstLoginTime;
	}




	public void setFirstLoginTime(Date firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
	}




	public Date getLastLoginTime() {
		return lastLoginTime;
	}




	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}




	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + account + '\'' +
            ", realname='" + realName + '\'' +
            ", dblink='" + super.getDblink() + '\'' +
            '}';
}
}