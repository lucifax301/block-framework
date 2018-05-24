package com.block.framework.web.controller;

import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.block.framework.common.model.ResultBean;
import com.block.framework.common.util.HttpUtil;
import com.block.framework.common.wx.WXUtil;

public abstract class WXController {

	public abstract String getAppId();
	
	public abstract String getSecret();
	
	public abstract ResultBean getUserInfo(@RequestParam String code)  ;
	
	
	public ResultBean doGetUserInfo(String code) {
		
		//通过code换取网页授权access_token
		String tokenUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+getAppId()+"&secret="+getSecret()+"&code="+code+"&grant_type=authorization_code";
		String response= HttpUtil.doGet(tokenUrl, "utf-8");
		JSONObject json= JSON.parseObject(response);
		String access_token="";
		String oppenId="";
		if(json.containsKey("access_token")){
			access_token=json.getString("access_token");
			oppenId=json.getString("openid");
		}
		System.out.println("access_token==========="+access_token);
		System.out.println("oppenId==========="+oppenId);
		
		
		//拉取用户信息
    	String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+oppenId+"&lang=zh_CN" ;
    	String infoResponse= HttpUtil.doGet(userInfoUrl, "utf-8");
		JSONObject infoJson= JSON.parseObject(infoResponse);
		ResultBean r = new ResultBean();
		r.setResult(infoJson);
		return r;
	}
	
	public abstract ResultBean geOpenId(@RequestParam String code)  ;
	
	public ResultBean doGetOpenId(String code) {
		String openId = WXUtil.getOpenId(getAppId(), getSecret(), code);
		ResultBean r = new ResultBean();
		r.setResult(openId);
		return r;
	}
}
