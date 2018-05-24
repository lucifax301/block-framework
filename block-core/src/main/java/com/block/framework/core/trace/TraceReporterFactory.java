package com.block.framework.core.trace;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

public class TraceReporterFactory {

	private String defaultReporter;
	
	private ConcurrentHashMap<String,TraceReporter> reporters = new ConcurrentHashMap<String,TraceReporter>(4);
	
	
	
	public String getDefaultReporter() {
		return defaultReporter;
	}



	public void setDefaultReporter(String defaultReporter) {
		this.defaultReporter = defaultReporter;
	}

	


	public ConcurrentHashMap<String, TraceReporter> getReporters() {
		return reporters;
	}



	public void setReporters(ConcurrentHashMap<String, TraceReporter> reporters) {
		this.reporters = reporters;
	}



	public TraceReporter getReporter(){
		return reporters.get(defaultReporter);
	}
	
	public TraceReporter getReporter(String name){
		return reporters.get(name);
	}
	
	private static TraceReporterFactory factory;
	
	public static TraceReporterFactory instance(){
		return factory;
	}
	
	//@PostConstruct 注解的方法在加载类的构造函数之后执行，也就是在加载了构造函数之后，执行init方法；
	@PostConstruct  
    public void init() { 
		factory = this;
	}
}
