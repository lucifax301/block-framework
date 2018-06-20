package com.block.framework.core.trace;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.block.framework.core.trace.reporter.NullTraceReporter;

public class TraceReporterFactory {

	private static String defaultReporter;
	
	private static ConcurrentHashMap<String,TraceReporter> reporters = new ConcurrentHashMap<String,TraceReporter>(4);
	
	private static TraceReporter Null = new NullTraceReporter();
	
	public static String getDefaultReporter() {
		return defaultReporter;
	}



//	public static void setDefaultReporter(String defaultReporter) {
//		this.defaultReporter = defaultReporter;
//	}

	


//	public ConcurrentHashMap<String, TraceReporter> getReporters() {
//		return reporters;
//	}
//
//
//
//	public void setReporters(ConcurrentHashMap<String, TraceReporter> reporters) {
//		this.reporters = reporters;
//	}



	public static TraceReporter getReporter(){
		TraceReporter reporter = reporters.get(defaultReporter);
		return reporter!=null?reporter:Null;
	}
	
	public static TraceReporter getReporter(String name){
		return reporters.get(name);
	}
	
	public synchronized static void addReporter(String name,TraceReporter reporter){
		System.out.println("################add reporter:"+name);
		reporters.put(name, reporter);
		if(reporters.isEmpty()){
			defaultReporter = name;
		}
	}
	
//	private static TraceReporterFactory factory;
//	
//	public static TraceReporterFactory instance(){
//		return factory;
//	}
	
	//@PostConstruct 注解的方法在加载类的构造函数之后执行，也就是在加载了构造函数之后，执行init方法；
//	@PostConstruct  
//    public void init() { 
//		factory = this;
//		
//	}
}
