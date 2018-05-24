package com.block.framework.core.trace;

public class InnerTrace {

	private String traceId;
	
	private String spanId;
	
	private int order;
	
	private InnerTrace parent;
	
	private long nanoStartTime;
	
	private long nanoEndTime;
	
	private long duration;
	
	private String methodName;
	
	private String ip;
	
	

	public String getSpanId() {
		return spanId;
	}

	public void setSpanId(String spanId) {
		this.spanId = spanId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getNanoStartTime() {
		return nanoStartTime;
	}

	public void setNanoStartTime(long nanoStartTime) {
		this.nanoStartTime = nanoStartTime;
	}

	public long getNanoEndTime() {
		return nanoEndTime;
	}

	public void setNanoEndTime(long nanoEndTime) {
		this.nanoEndTime = nanoEndTime;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public InnerTrace getParent() {
		return parent;
	}

	public void setParent(InnerTrace parent) {
		this.parent = parent;
	}
	
	public void close(){
		/**
		 * send to queue
		 */
	}
}
