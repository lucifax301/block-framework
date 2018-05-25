package com.block.framework.core.trace.reporter;

import javax.annotation.PostConstruct;

import com.block.framework.core.trace.InnerTrace;
import com.block.framework.core.trace.TraceReporter;
import com.block.framework.core.trace.TraceSender;

public abstract class SyncTraceReporter implements TraceReporter {

	private TraceSender sender = null;
	
	@Override
	public boolean report(InnerTrace innerTrace) {
		sender.send(innerTrace);
		return true;
	}

	@PostConstruct  
	public void init(){
		sender = getSender();
	}
	
	public abstract TraceSender getSender();
}
