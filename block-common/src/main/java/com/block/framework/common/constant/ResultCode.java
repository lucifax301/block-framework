package com.block.framework.common.constant;

import java.util.concurrent.ConcurrentHashMap;

import com.block.framework.common.exception.ConfigException;

public class ResultCode {

	public class RESULTKEY {
        public static final String CODE="code";
        public static final String MSGKEY="msgKey";
        public static final String MSGINFO="msgInfo";
        public static final String DATA="data";
    }
    public class ERRORCODE {
        public static final int SUCCESS=0;
        public static final int NEEDLOGIN=1;
        public static final int NOAUTH=2;
        public static final int PARAMERROR=3;
        public static final int FAILED=4;
        public static final int EXCEPTION=5;
        public static final int DATAEXIST=6;
        public static final int COACHAPPLYEXIST=7;
        public static final int APPLYHASAUDIT=8;
        public static final int PAUSEAPPLYEXIST=9;
        
        public static final int USER_LOGIN_FAIL=10;
        public static final int USER_ACCOUNT_BAN=11;
        public static final int AUTHCODE_ERROR=12;
        public static final int USER_PASSWORD_ERROR=13;
        public static final int USER_NOT_EXIT=14;
		public static final int MOBILE_NUMBER_ERROR = 15;
		public static final int MOBILE_NOT_EXIT_ERROR = 16;
    }
    public class ERRORINFO {
        public static final String SUCCESS="操作成功";
        public static final String NEEDLOGIN="该操作需要登录";
        public static final String NOAUTH="您没有该项操作的权利";
        public static final String PARAMERROR="操作参数错误，请更正后重试";
        public static final String FAILED="操作失败，请重试";
        public static final String EXCEPTION="网络连接失败";
        public static final String DATAEXIST="数据已经存在";
        public static final String COACHAPPLYEXIST="该教练当前已有修改申请正在等待审核，不能再次提交修改申请哦。";
        public static final String APPLYHASAUDIT="数据已经被审核通过或者审核不通过";
        public static final String PAUSEAPPLYEXIST="该学员当前已有申请正在等待审核，不能再次提交申请哦。";
        
        public static final String USER_LOGIN_FAIL="帐号或密码错误，请重新输入";
        public static final String USER_ACCOUNT_BAN="帐号已停用";
        public static final String AUTHCODE_ERROR="验证码错误";
        public static final String USER_PASSWORD_ERROR="旧密码错误，请重新输入";
        public static final String USER_NOT_EXIT="用户不存在";
        public static final String MOBILE_NUMBER_ERROR="手机号码错误";
        public static final String MOBILE_NOT_EXIT_ERROR="手机号码不存在";
    }
    
    private static ConcurrentHashMap<String,Integer> codeInfo=new ConcurrentHashMap<String,Integer>();
    private static ConcurrentHashMap<Integer,String> msgInfo=new ConcurrentHashMap<Integer,String>();
    public static Integer getCode(String name){
    	return codeInfo.get(name);
    }
	public static String getCodeInfo(int code){
		  return msgInfo.get(code);
	}
	
	/**
	 * 业务自定义错误全部10000起步
	 * @param code 必须小于10000
	 * @param info
	 */
	public static void addCodeInfo(String name,int code,String info){
		if(code>=10000){
			throw new ConfigException(name+" code can not > 10000");
		}
		int key = 10000+code;
		if(codeInfo.contains(name)){
			throw new ConfigException(name+" is exist in ResultCode Map");
		}
		codeInfo.put(name, key);
		msgInfo.put(code, info);
	}
}
