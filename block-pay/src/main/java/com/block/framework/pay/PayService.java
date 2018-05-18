package com.block.framework.pay;

public interface PayService<R> {

	PayReqResult pay(PayVo pay);

	PayReqResult payCallback(R result);
}
