package com.block.framework.mq;

public interface MQService {

	void sendMessae(MQMessage<?> message);
}
