package com.block.framework.pay;

public abstract class PayAction {

	/**
     * 进行下单行为时,进行付款前的预处理
     * @param payVo
     * @param reqResult
     * @throws Exception
     */
    public abstract void doPayAction(PayVo payVo, PayReqResult reqResult) throws Exception;

    /**
     * 当付款结束时,进行回调处理
     * @param callbackParam
     * @return
     */
    public abstract PayReqResult payCallBack(Object ... callbackParam);
    
    public abstract IPayPurpose getPayPurposeHandler(PurposeType purposeType);
}
