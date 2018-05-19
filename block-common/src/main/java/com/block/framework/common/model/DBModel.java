package com.block.framework.common.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DBModel extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2507716482717109018L;
	// 默认不走管理库
	@JsonIgnore
	private boolean mgrdb = false;
	// 所属数据库
	@JsonIgnore
	private String dblink;
	// 查询关键字或 excel验证消息
	private String condition;

	@JsonIgnore
	private Integer pageNo = 1;
	@JsonIgnore
	private Integer pageSize = 10;

	private Date beginDate;
	
	private Date endDate;
	
	// 排序
	@JsonIgnore
	private String orders;
	
	

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isMgrdb() {
		return mgrdb;
	}

	public void setMgrdb(boolean mgrdb) {
		this.mgrdb = mgrdb;
	}

	public String getDblink() {
		return dblink;
	}

	public void setDblink(String dblink) {
		this.dblink = dblink;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	
}
