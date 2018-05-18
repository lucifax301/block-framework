package com.block.framework.pay;

import java.util.Date;

public interface IPayPurpose {

	/**
     * 支付前预处理
     * @param payVo
     * @param reqResult
     * @return
     */
    Object purposeAdvance(final PayVo payVo, final PayReqResult reqResult) throws Exception;
    
    /**
     * 支付结果回调处理
     * @param payVo
     * @param endTime
     * @param totalFee
     * @param waterNum 
     * @return
     */
    PayReqResult doPurpose(PayVo payVo, Date endTime, String waterNum, int totalFee);
}
