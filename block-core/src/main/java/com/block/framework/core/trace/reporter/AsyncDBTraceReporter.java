package com.block.framework.core.trace.reporter;

import javax.annotation.PostConstruct;

import com.block.framework.core.trace.TraceReporterFactory;
import com.block.framework.core.trace.TraceSender;
import com.block.framework.core.trace.TraceSenderFactory;

public class AsyncDBTraceReporter extends AsyncTraceReporter {

//	@Autowired
//	private TraceSenderFactory traceSenderFactory;
	
	@Override
	public TraceSender getSender() {
		//return traceSenderFactory.getSender("db");
		return TraceSenderFactory.getSender("db");
	}

	@PostConstruct  
	public void init(){
		TraceReporterFactory.addReporter("async", this);
	}
}
