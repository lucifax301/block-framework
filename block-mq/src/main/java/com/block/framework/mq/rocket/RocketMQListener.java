package com.block.framework.mq.rocket;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

public class RocketMQListener implements MessageListenerConcurrently {

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
			ConsumeConcurrentlyContext context) {
		for(MessageExt msg : msgs){
			String topic = msg.getTopic();
			
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
