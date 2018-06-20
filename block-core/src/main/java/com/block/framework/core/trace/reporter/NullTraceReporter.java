package com.block.framework.core.trace.reporter;

import com.block.framework.core.trace.InnerTrace;
import com.block.framework.core.trace.TraceReporter;

public class NullTraceReporter implements TraceReporter {

	@Override
	public boolean report(InnerTrace innerTrace) {
		/**
		 * donothing
		 */
		return true;
	}

}
