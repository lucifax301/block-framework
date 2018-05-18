package com.block.framework.pay;

import com.block.framework.common.constant.ResultCode;

public class PayConfig {

	public static void init(){
		ResultCode.addCodeInfo("ORDER_PAY_SIGN_INVALID", 5005, "订单支付参数校验失败，可能被篡改");
		ResultCode.addCodeInfo("ORDER_PAY_MONEY_NOTENOUGH", 5006, "订单支付失败，余额不足");
		ResultCode.addCodeInfo("ORDER_CLOSED", 5007, "订单已关闭，请重新支付");
		ResultCode.addCodeInfo("ORDER_LOCK", 5059, "支付请求锁定");
		
	}
}
