package com.block.framework.sms.alibaba;

public class SmsConfig {

	private String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private String accessKeyId = "LTAIO2GWU3zgUrqK";
    private String accessKeySecret = "gX7xPLpu8WC8Zn8efHyWf6iXZIcorq";
	public String getProduct() {
		return product;
	}
	
	
	public String getDomain() {
		return domain;
	}


	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
    
    
}
