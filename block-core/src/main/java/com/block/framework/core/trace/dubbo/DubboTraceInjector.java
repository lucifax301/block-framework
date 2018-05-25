package com.block.framework.core.trace.dubbo;

import java.util.Map;

import com.alibaba.dubbo.rpc.RpcContext;
import com.block.framework.core.trace.InnerTrace;
import com.block.framework.core.trace.TraceInjector;

public class DubboTraceInjector implements TraceInjector<RpcContext> {

	@Override
	public void inject(InnerTrace innerTrace, RpcContext carrier) {
		Map<String,String> attachments = carrier.getAttachments();
		
		attachments.put(InnerTrace.TRACE_ID, innerTrace.getTraceId());
		attachments.put(InnerTrace.SPAN_ID, innerTrace.getSpanId());
		attachments.put(InnerTrace.TRACE_ORDER, String.valueOf(innerTrace.getOrder()));
	}

}
