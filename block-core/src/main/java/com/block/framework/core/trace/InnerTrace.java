package com.block.framework.core.trace;

import java.io.Serializable;
import java.util.Date;

import com.block.framework.common.model.BuModel;

public class InnerTrace extends BuModel implements Serializable{

	public static final String TRACE_ID = "trace_id";
	public static final String SPAN_ID = "span_id";
	public static final String TRACE_ORDER = "trace_order";
	
	private int id;
	
	private String traceId;
	
	private String spanId;
	
	private String parentSpanId;
	
	private int order;
	
	private InnerTrace parent;
	
	private long nanoStartTime;
	
	private Date startTime;
	
	private long nanoEndTime;
	
	private Date endTime;
	
	private long duration;
	
	private String methodName;
	
	private String ip;
	
	//自定义， 在controller里用 web_request,在普通方法里用service
	private String serviceType;
	
	private int step;
	
	
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public InnerTrace(){
		
	}
	
	public InnerTrace(TraceBuilder builder){
		this.traceId=builder.traceId;
		this.spanId=builder.spanId;
		this.order=builder.order;
		this.methodName=builder.methodName;
		this.ip=builder.ip;
		
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	
	
	public String getParentSpanId() {
		return parentSpanId;
	}

	public void setParentSpanId(String parentSpanId) {
		this.parentSpanId = parentSpanId;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public void close(){
		/**
		 * send to queue
		 */
	}
	
	public static class TraceBuilder{
		private String traceId;
		
		private String spanId;
		
		private int order;
		
		private long nanoStartTime;
		
		private long nanoEndTime;
		
		private long duration;
		
		private String methodName;
		
		private String ip;
		
		public InnerTrace.TraceBuilder traceId(String traceId){
			this.traceId=traceId;
			return this;
		}
		
		public InnerTrace.TraceBuilder spanId(String spanId){
			this.spanId=spanId;
			return this;
		}
		
		public InnerTrace.TraceBuilder order(int order){
			this.order=order;
			return this;
		}
		
		public InnerTrace.TraceBuilder begin(long begin){
			this.nanoStartTime=begin;
			return this;
		}
		public InnerTrace.TraceBuilder end(long end){
			this.nanoEndTime=end;
			this.duration=this.nanoEndTime-this.nanoStartTime;
			return this;
		}
		public InnerTrace.TraceBuilder methodName(String methodName){
			this.methodName=methodName;
			return this;
		}
		public InnerTrace.TraceBuilder ip(String ip){
			this.ip=ip;
			return this;
		}
		
		public InnerTrace build(){
			return new InnerTrace(this);
		}
	}
	
	public static TraceBuilder builder(){
		return new TraceBuilder();
	}
}
