package com.block.framework.sms.tencent;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.sms.SendSmsResult;
import com.block.framework.sms.SmsService;
import com.block.framework.sms.SmsServiceFactory;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

public class TencentSmsServiceImpl implements SmsService<String[]> {

	@Autowired
	private TencentSmsConfig tencentSmsConfig;
	
	@Override
	public SendSmsResult sendSms(String mobile, String templateId,
			String[] content) throws Exception {
		try {
		    String[] params = content;
		    int tid = Integer.parseInt(templateId);
		    SmsSingleSender ssender = new SmsSingleSender(tencentSmsConfig.getAppid(), tencentSmsConfig.getAppkey());
		    SmsSingleSenderResult result = ssender.sendWithParam("86", mobile,
		    		tid, params, tencentSmsConfig.getSmsSign(), "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
		    System.out.print(result);
		    
		    return new SendSmsResult(result.result+"",result.errMsg);
		} catch (HTTPException e) {
		    // HTTP响应码错误
		    e.printStackTrace();
		    return new SendSmsResult("1","网络错误");
		} catch (JSONException e) {
		    // json解析错误
		    e.printStackTrace();
		    return new SendSmsResult("1","JSON错误");
		} catch (IOException e) {
		    // 网络IO错误
		    e.printStackTrace();
		    return new SendSmsResult("1","IO错误");
		}
	}

	@PostConstruct  
	public void init(){
		SmsServiceFactory.addService("tencent", this);
	}
}
