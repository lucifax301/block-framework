package com.block.framework.core.trace;

import com.block.framework.core.context.RequestContext;



public class Trace {

	private static ThreadLocal<InnerTrace> current = new ThreadLocal<InnerTrace>();
	
	public static InnerTrace getCurrentTrace(){
		return current.get();
	}
	
	public static InnerTrace createTrace(String name){
		long start = System.nanoTime();
		InnerTrace innerTrace = current.get();
		if(innerTrace==null){
			innerTrace = new InnerTrace();
			current.set(innerTrace);
		}else{
			InnerTrace parent = innerTrace;
			innerTrace = new InnerTrace();
			innerTrace.setParent(parent);
			innerTrace.setOrder(parent.getOrder()+1);
			current.set(innerTrace);
		}
		String traceId = RequestContext.get().getTraceId();
		innerTrace.setTraceId(traceId);
		innerTrace.setMethodName(name);
		String spanId = TraceUtil.createTraceIdString();
		innerTrace.setSpanId(spanId);
		innerTrace.setNanoStartTime(start);
		return innerTrace;
	}
	
	public static InnerTrace createTrace(String name,InnerTrace parent){
		if(parent==null){
			return createTrace(name);
		}
		InnerTrace innerTrace = new InnerTrace();
		innerTrace.setParent(parent);
		innerTrace.setTraceId(parent.getTraceId());
		innerTrace.setMethodName(name);
		innerTrace.setOrder(parent.getOrder()+1);
		long start = System.nanoTime();
		innerTrace.setNanoStartTime(start);
		String spanId = TraceUtil.createTraceIdString();
		innerTrace.setSpanId(spanId);
		current.set(innerTrace);
		return innerTrace;
	}
	
	public static InnerTrace endTrace(){
		InnerTrace innerTrace = current.get();
		if(innerTrace!=null){
			InnerTrace parent = innerTrace.getParent();
			current.set(parent);
			innerTrace.setNanoEndTime(System.nanoTime());
			innerTrace.setDuration(innerTrace.getNanoStartTime()-innerTrace.getNanoEndTime());
			innerTrace.close();
			return innerTrace;
		}
		return null;
	}
	
	private static TraceReporterFactory traceReporterFactory=TraceReporterFactory.instance();
	
	public static void close(InnerTrace innerTrace){
		traceReporterFactory.getReporter().report(innerTrace);
	}
}
