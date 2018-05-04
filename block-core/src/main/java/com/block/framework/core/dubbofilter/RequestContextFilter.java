package com.block.framework.core.dubbofilter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.block.framework.core.context.RequestContext;
import com.block.framework.core.dubbo.BlockInvokeContext;


/**
 * tomcat项目了加载类的顺序是class再到lib, 我们这里重写了一个RpcInvocation(包路径和 dubbo的一�?
 * ，替换dubbo里RpcInvocation,由于先加载我们定义的，dubbo自身的就不会再加�?
 * @author Administrator
 *
 */
@Activate(group={Constants.PROVIDER,Constants.CONSUMER},order=1000,before={"exception"})
public class RequestContextFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		if(RpcContext.getContext().isConsumerSide()){
			return invokeConsumerSide(invoker,invocation);
		}else{
			return invokeProviderSide(invoker,invocation);
		}
	}
	
	private Result invokeConsumerSide(Invoker<?> invoker, Invocation invocation){
		try{
			BlockInvokeContext ctx = setContext(invocation);
			return invoker.invoke(invocation);
		}finally{
			takeContext(invocation);
		}
	}
	
	private BlockInvokeContext takeContext(Invocation invocation){
		BlockInvokeContext ctx= ((RpcInvocation)invocation).getInvokeContext();
		if(ctx!=null){
			((RpcInvocation)invocation).setInvokeContext(null);
			return ctx;
		}
		return null;
	}

	private BlockInvokeContext setContext(Invocation invocation){
		BlockInvokeContext ctx = new BlockInvokeContext();
		ctx.setRequestContext(RequestContext.get());
		if(ctx.getRequestContext()==null){
			throw new RpcException("上下文为空，禁止调用");
		}
		((RpcInvocation)invocation).setInvokeContext(ctx);
		return ctx;
	}
	
	private Result invokeProviderSide(Invoker<?> invoker, Invocation invocation){
		BlockInvokeContext ctx = takeContext(invocation);
		try{
			setupProviderSideContext(ctx);
			return invoker.invoke(invocation);
		}catch(Throwable e){
			throw new RpcException(e);
		}finally{
			RequestContext.set(null);
		}
	}

	private void  setupProviderSideContext(BlockInvokeContext ctx){
		RequestContext rc = RequestContext.get();
		RequestContext.set(rc);
	}
}
