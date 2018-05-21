package com.block.framework.sms;

import java.io.Serializable;

public class SendSmsResult implements Serializable {
	
	public SendSmsResult(){}
	
	public SendSmsResult(String code,String message){
		this.code=code;
		this.message=message;
	}

	private String code;
	
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
