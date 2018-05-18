package com.block.framework.pay.wxpay;

import java.util.concurrent.TimeUnit;

import org.redisson.core.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.common.constant.ResultCode;
import com.block.framework.common.util.StringUtil;
import com.block.framework.pay.IPayPurposeService;
import com.block.framework.pay.PayFactory;
import com.block.framework.pay.PayReqResult;
import com.block.framework.pay.PayService;
import com.block.framework.pay.PayVo;
import com.block.framework.pay.PayWayType;
import com.block.framework.pay.PurposeServiceFactory;
import com.block.framework.pay.PurposeType;
import com.block.framework.pay.util.PayConstants;
import com.block.framework.redis.DistributedRedisLock;

public class WXPayService implements PayService<String> {

	private static Logger logger = LoggerFactory.getLogger(WXPayService.class);
	
	@Autowired
	private DistributedRedisLock dlock;
	
	@Autowired
	private PurposeServiceFactory purposeServiceFactory;
	
	@Autowired
	private PayFactory payFactory;
	
	@Override
	public PayReqResult pay(PayVo pay) {
		long t1 = System.currentTimeMillis();
        PayReqResult r = PayReqResult.getFailed();
        Long userId = pay.getUserId();
        RLock lock = null;
        try {
            lock = dlock.getLock(PayConstants.REDISKEY.LOCK_PAY_USER + userId);
            boolean hasLock = lock.tryLock(1, 10, TimeUnit.SECONDS);//1s等待，10s超时，防止死锁

            if (hasLock) {
            	
            	IPayPurposeService service = purposeServiceFactory.getHandler(pay.getPayPurpose());
            	service.purposeAdvanceMoney(pay);
            	

//                // 校验一下参数
//                if (pay.getPayPurpose() != PurposeType.COURSE
//                        && (pay.getPayOrderId() == null || pay.getPayOrderId().isEmpty())) {
//                    //生成一个订单ID
//                    pay.setPayOrderId(StringUtil.getOrderId());
//                }
//
//                // 客户端有时候没传remark
//                if (pay.getRemark() == null || pay.getRemark().isEmpty()) {
//                    pay.setRemark(pay.getPayPurpose().getDesc());
//                }

                payFactory.getPayAction(pay.getPayWay()).doPayAction(pay, r);

            } else {
                logger.error("PayServiceImplNew error! studentId=" + userId + " add pay LOCK ERROR.");
                r.setCode(ResultCode.getCode("ORDER_LOCK"));
                r.setMsgInfo(ResultCode.getCodeInfo(ResultCode.getCode("ORDER_LOCK")));
            }
        } catch (Exception e) {
            logger.error("PayServiceImplNew error! payVo:" + pay + " " + e);
            //20160727 用户支付异常是大事，需要邮件给开发，及时定位
            //emailService.send("【系统】[用户支付异常啦，请抓紧时间处理！！]", ""+pay+"|Exception:"+e);
        } finally {
            if (lock != null) {
                try {
                    lock.unlock();
                } catch (Exception e) {
                }
            }
        }

        long t2 = System.currentTimeMillis();
        logger.debug("PayServiceImplNew time takes " + (t2 - t1));

        return r;
	}

	@Override
	public PayReqResult payCallback(String result) {
		PayReqResult r = PayReqResult.getFailed();
        try {
            r = payFactory.getPayAction(PayWayType.WX).payCallBack(result);
        } catch (Exception e) {
            logger.error("result:" + result, e);
        }
        return r;
	}

}
