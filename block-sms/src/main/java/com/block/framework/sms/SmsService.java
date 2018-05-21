package com.block.framework.sms;

public interface SmsService <P>{

	SendSmsResult sendSms(String mobile,String templateId,P content) throws Exception;
}
