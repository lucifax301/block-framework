package com.block.framework.mq.rocket;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.block.framework.common.util.SerializableUtil;
import com.block.framework.mq.MQException;
import com.block.framework.mq.MQMessage;
import com.block.framework.mq.MQService;

public class RocketMQService implements MQService {

	private DefaultMQProducer logProducer;
	
	@Override
	public void sendMessae(MQMessage<?> message) {
		try{
			Message msg=new Message();
			msg.setTopic(message.getTopic());
			msg.setTags(message.getTags());
			msg.setBody(SerializableUtil.serialize(message.getBody()));
			logProducer.send(msg);
		}catch(Exception ex){
			throw new MQException("RocketMQ send exception,",ex);
		}

	}

}
