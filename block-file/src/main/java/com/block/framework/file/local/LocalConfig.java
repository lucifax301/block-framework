package com.block.framework.file.local;

public class LocalConfig {

	private String domain;
	
	//本地文件路径
	private String path;
	
	//* all, or jpg,png,doc,zip
	private String acceptSuffix;
	
	

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAcceptSuffix() {
		return acceptSuffix;
	}

	public void setAcceptSuffix(String acceptSuffix) {
		this.acceptSuffix = acceptSuffix;
	}
	
	
}
