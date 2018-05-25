package com.block.framework.core.dubbo.filter;

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
import com.block.framework.core.trace.dubbo.DubboTraceInjector;

@Activate(group={Constants.CONSUMER},order=-9000)
public class TraceConsumerFilter implements Filter {

	String ip = NetAddressUtil.getLocalIpAddress();
	
	DubboTraceInjector injector = new DubboTraceInjector();
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		try{
			String name = "consumer["+ip+"]"+invocation.getMethodName();
			InnerTrace innerTrace = Trace.createTrace(name);
			injector.inject(innerTrace, RpcContext.getContext());
			Result result = invoker.invoke(invocation);
			return result;
		}finally{
			//InnerTrace current = Trace.getCurrentTrace();
			Trace.endTrace();
		}
	}

}
