package com.block.framework.core.trace.reporter;

import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.core.trace.TraceSender;
import com.block.framework.core.trace.TraceSenderFactory;

public class AsyncDBTraceReporter extends AsyncTraceReporter {

	@Autowired
	private TraceSenderFactory traceSenderFactory;
	
	@Override
	public TraceSender getSender() {
		return traceSenderFactory.getSender("db");
	}

}
