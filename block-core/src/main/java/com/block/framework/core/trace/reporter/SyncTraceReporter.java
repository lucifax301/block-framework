package com.block.framework.core.trace.reporter;

import com.block.framework.core.trace.InnerTrace;
import com.block.framework.core.trace.TraceReporter;
import com.block.framework.core.trace.TraceSender;

public abstract class SyncTraceReporter implements TraceReporter {

	private TraceSender sender = getSender();
	
	@Override
	public boolean report(InnerTrace innerTrace) {
		sender.send(innerTrace);
		return true;
	}

	public abstract TraceSender getSender();
}
