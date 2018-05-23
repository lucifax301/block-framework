package com.block.framework.file;

import java.io.Serializable;

public class UploadResult implements Serializable {

	public static final int UPLOAD_SUCCESS=0;
	public static final int UPLOAD_FAILED=1;
	
	private String path;
	//0 upload success ,1 failed
	private int code;
	
	private String msg;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
