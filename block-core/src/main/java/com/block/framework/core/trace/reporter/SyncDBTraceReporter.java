package com.block.framework.core.trace.reporter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.core.trace.TraceReporterFactory;
import com.block.framework.core.trace.TraceSender;
import com.block.framework.core.trace.TraceSenderFactory;

public class SyncDBTraceReporter extends SyncTraceReporter {

//	@Autowired
//	private TraceSenderFactory traceSenderFactory;
	
	@Override
	public TraceSender getSender() {
		//return traceSenderFactory.getSender("db");
		return TraceSenderFactory.getSender("db");
	}

	@PostConstruct  
	public void init(){
		TraceReporterFactory.addReporter("sync", this);
	}
}
