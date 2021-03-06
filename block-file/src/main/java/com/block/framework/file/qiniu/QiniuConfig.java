package com.block.framework.file.qiniu;

import javax.annotation.PostConstruct;

public class QiniuConfig {

//	private String bucklet = "liliyun";
//	private String ak = "wTZfpMov09_Pvgpzt01kVbTGoFKMcMf2CUmYs5n2";
//	private String sk = "CzQMFHoGeNOVuF_0sG96oFzrQtVdRx25-aQrudp1";	
//	private String domain = "http://7xnvu2.com1.z0.glb.clouddn.com/";
	private String bucklet ;
	private String ak ;
	private String sk ;	
	private String domain ;
	
	private String zone;
	
	private String localDir;
	
	public String getBucklet() {
		return bucklet;
	}
	public void setBucklet(String bucklet) {
		this.bucklet = bucklet;
	}
	public String getAk() {
		return ak;
	}
	public void setAk(String ak) {
		this.ak = ak;
	}
	public String getSk() {
		return sk;
	}
	public void setSk(String sk) {
		this.sk = sk;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	
	
	public String getLocalDir() {
		return localDir;
	}
	public void setLocalDir(String localDir) {
		this.localDir = localDir;
	}


	private static QiniuConfig config = null;
	
	@PostConstruct 
	public void init() { 
		config = this;
	}
	
	public static QiniuConfig getConfig(){
		return config;
	}
}
