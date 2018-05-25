package com.block.framework.core.trace;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

public class TraceSenderFactory {

	private String defaultSender;
	
	private ConcurrentHashMap<String,TraceSender> senders = new ConcurrentHashMap<String,TraceSender>(4);

	public String getDefaultSender() {
		return defaultSender;
	}

	public void setDefaultSender(String defaultSender) {
		this.defaultSender = defaultSender;
	}

	public ConcurrentHashMap<String, TraceSender> getSenders() {
		return senders;
	}

	public void setSenders(ConcurrentHashMap<String, TraceSender> senders) {
		this.senders = senders;
	}
	
	public TraceSender getSender(){
		return senders.get(defaultSender);
	}
	
	public TraceSender getSender(String name){
		return senders.get(name);
	}
	@PostConstruct 
	public void init(){
		System.out.println("####################post contrauct TraceSenderFactory");
	}
}
