package com.block.framework.mq;

public class MQMessage<T> {

	private String topic;
	
	private String tags;
	
	private T body;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
	
}
