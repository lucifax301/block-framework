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
	
	public ResultBean getUserInfo(@RequestParam String code) {
		return this.doGetUserInfo(code);
	}
	
	/**
	 * 可以参考文档
	 * https://www.w3cschool.cn/weixinkaifawendang/pdja1qcy.html
	 * 
	 */
	
	private ResultBean doGetUserInfo(String code) {
		
		//通过code换取网页授权access_token
		String tokenUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+getAppId()+"&secret="+getSecret()+"&code="+code+"&grant_type=authorization_code";
		String response= HttpUtil.doGet(tokenUrl, "utf-8");
		JSONObject json= JSON.parseObject(response);
		String access_token="";
		String oppenId="";
		/**
		 * { "access_token":"ACCESS_TOKEN",    

			 "expires_in":7200,    
			
			 "refresh_token":"REFRESH_TOKEN",    
			
			 "openid":"OPENID",    
			
			 "scope":"SCOPE" }
			 
			  {"errcode":40029,"errmsg":"invalid code"} 
		 */
		if(json.containsKey("access_token")){
			access_token=json.getString("access_token");
			oppenId=json.getString("openid");
		}
		System.out.println("access_token==========="+access_token);
		System.out.println("oppenId==========="+oppenId);
		
		
		//拉取用户信息
    	String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+oppenId+"&lang=zh_CN" ;
    	String infoResponse= HttpUtil.doGet(userInfoUrl, "utf-8");
    	/**
    	 * 正确时返回的JSON数据包如下：

			{    "openid":" OPENID",  
			
			 " nickname": NICKNAME,   
			
			 "sex":"1",   
			
			 "province":"PROVINCE"   
			
			 "city":"CITY",   
			
			 "country":"COUNTRY",    
			
			 "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ
			
			4eMsv84eavHiaiceqxibJxCfHe/46",  
			
			"privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],    
			
			 "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL" 
			
			} 
			
			{"errcode":40003,"errmsg":" invalid openid "}
    	 */
    	System.out.println("infoResponse:"+infoResponse);
		JSONObject infoJson= JSON.parseObject(infoResponse);
		ResultBean r = new ResultBean();
		r.setResult(infoJson);
		return r;
	}
	
	public ResultBean getOpenId(@RequestParam String code) {
		return this.doGetOpenId(code);
	}
	
	private ResultBean doGetOpenId(String code) {
		String openId = WXUtil.getOpenId(getAppId(), getSecret(), code);
		ResultBean r = new ResultBean();
		r.setResult(openId);
		return r;
	}
}
