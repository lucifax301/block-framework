package com.block.demo.demo;

import java.io.Serializable;

import com.block.framework.common.model.BaseModel;
import com.block.framework.common.model.DBModel;

public class DemoModel extends DBModel implements Serializable {

	private int id;
	private String name;
	private String val;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String value) {
		this.val = value;
	}
	
	
}
