package com.block.framework.core.trace;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

public class TraceSenderFactory {

	private static String defaultSender;
	
	private static ConcurrentHashMap<String,TraceSender> senders = new ConcurrentHashMap<String,TraceSender>(4);

	public static String getDefaultSender() {
		return defaultSender;
	}

//	public void setDefaultSender(String defaultSender) {
//		this.defaultSender = defaultSender;
//	}

//	public ConcurrentHashMap<String, TraceSender> getSenders() {
//		return senders;
//	}

//	public void setSenders(ConcurrentHashMap<String, TraceSender> senders) {
//		this.senders = senders;
//	}
	
	public static TraceSender getSender(){
		return senders.get(defaultSender);
	}
	
	public static TraceSender getSender(String name){
		return senders.get(name);
	}
	
	public synchronized static void addReporter(String name,TraceSender sender){
		senders.put(name, sender);
		if(senders.isEmpty()){
			defaultSender = name;
		}
	}
	
//	@PostConstruct 
//	public void init(){
//		System.out.println("####################post contrauct TraceSenderFactory");
//	}
}
