package com.block.framework.common.wx;

import java.util.HashMap;
import java.util.Map;







import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.block.framework.common.util.EncryptUtil;
import com.block.framework.common.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class WXUtil {
	
	private static Logger logger = LoggerFactory.getLogger(WXUtil.class);

	public static String getOpenId(String appId,String secret,String code)
    {
        String openId = "";
        //String appId = "wxbbde6ffce207719a";
        //String secret = "f32ad5e73435181bed9e5cbbd3a0e9e8";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appId);
        params.put("secret", secret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");

        String result = HttpUtil.doGet(url, params, "UTF-8");
        Gson gson = new Gson();
        try
        {
            OpenIdRes openIdRes = gson.fromJson(result, OpenIdRes.class);
            openId = openIdRes.getOpenid();
            return openId;
        }
        catch (JsonSyntaxException e)
        {
            logger.error("get openId error");
        }
        return openId;
    }
	
	
	static String weixinTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
	
	static String weixinTicketUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi";
	
	public static Map getWeixinToken(String appid,String secret, String noncestr,String timestamp,String url) {
//		weixinAppid="wxbbde6ffce207719a";
//		weixinSecret="f32ad5e73435181bed9e5cbbd3a0e9e8";
//		weixinTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
//		weixinTicketUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi";
		String step1url=weixinTokenUrl.replaceAll("\\{0\\}", appid).replaceAll("\\{1\\}", secret);
		
			//log.debug("****************************request url is:"+step1url);
			String response= HttpUtil.doGet(step1url, "utf-8") ;
			//log.debug("****************************request response is:"+response);
			JSONObject json= JSON.parseObject(response);
			String token="";
			String ticket="";
			String sign="";
			if(json.containsKey("access_token")){
				token=json.getString("access_token");
				
				String step2url=weixinTicketUrl.replaceAll("\\{0\\}", token);
				//log.debug("****************************request url is:"+step2url);
				String ticketresp= HttpUtil.doGet(step2url, "utf-8") ;
				//log.debug("****************************request url is:"+ticketresp);
				JSONObject ticketjson=JSON.parseObject(ticketresp);
				if(ticketjson.containsKey("errcode")){
					if("0".equals(ticketjson.getString("errcode"))){
						if(ticketjson.containsKey("ticket")){
							ticket=ticketjson.getString("ticket");
							
							String source="jsapi_ticket="+ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
							sign=EncryptUtil.SHA1(source);
						}
					}
				}
			}
			Map map = new HashMap(4);
			map.put("token", token);
			map.put("ticket", ticket);
			map.put("sign", sign);
			

		return map;
	}
	
}
