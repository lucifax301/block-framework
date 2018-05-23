package com.block.framework.file.local;

public class LocalConfig {

	private String path;
	
	//* all, or jpg,png,doc,zip
	private String acceptSuffix;

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
