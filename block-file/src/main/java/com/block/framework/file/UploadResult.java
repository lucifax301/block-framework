package com.block.framework.file;

import java.io.Serializable;

public class UploadResult implements Serializable {

	public static final int UPLOAD_SUCCESS=0;
	public static final int UPLOAD_FAILED=1;
	
	//网络访问地址
	private String url;
	//本地文件可访问地址,如果是cdn 为空
	private String path;
	//0 upload success ,1 failed
	private int code;
	
	private String msg;
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
