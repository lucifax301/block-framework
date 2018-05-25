package com.block.framework.core.trace.dubbo;

import java.util.Map;

import com.alibaba.dubbo.rpc.RpcContext;
import com.block.framework.core.trace.InnerTrace;
import com.block.framework.core.trace.InnerTrace.TraceBuilder;
import com.block.framework.core.trace.TraceExtractor;

public class DubboTraceExtractor implements TraceExtractor<RpcContext> {

	@Override
	public InnerTrace attachTrace(RpcContext carrier) {
		Map<String,String> attachments = carrier.getAttachments();
		String traceId = attachments.get(InnerTrace.TRACE_ID);
		String spanId = attachments.get(InnerTrace.SPAN_ID);
		String order = attachments.get(InnerTrace.TRACE_ORDER);
		
		TraceBuilder builder = InnerTrace.builder().traceId(traceId).spanId(spanId).order(Integer.parseInt(order));
		return builder.build();
	}

}
