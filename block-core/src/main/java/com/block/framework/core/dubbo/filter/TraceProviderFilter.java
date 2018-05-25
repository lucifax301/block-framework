package com.block.framework.core.dubbo.filter;

import java.util.Map;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.block.framework.common.util.NetAddressUtil;
import com.block.framework.core.trace.InnerTrace;
import com.block.framework.core.trace.Trace;
import com.block.framework.core.trace.dubbo.DubboTraceExtractor;

@Activate(group={Constants.PROVIDER},order=-7000)
public class TraceProviderFilter implements Filter {

	String ip = NetAddressUtil.getLocalIpAddress();
	
	DubboTraceExtractor extractor = new DubboTraceExtractor();
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		try{
			Map<String,String> attachments = RpcContext.getContext().getAttachments();
			InnerTrace parent = extractor.attachTrace(RpcContext.getContext());
			String name = "provider["+ip+"]"+invocation.getMethodName();
			Trace.createTrace(name, parent);
			Result result = invoker.invoke(invocation);
			return result;
		}finally{
			Trace.endTrace();
		}
	}

}
