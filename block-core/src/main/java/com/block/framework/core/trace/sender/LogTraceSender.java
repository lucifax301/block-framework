package com.block.framework.core.trace.sender;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.block.framework.core.trace.InnerTrace;
import com.block.framework.core.trace.TraceSender;

public class LogTraceSender implements TraceSender {

	private static Logger logger = LoggerFactory.getLogger(LogTraceSender.class);
	
	@Override
	public void send(List<InnerTrace> traces) {
		for(InnerTrace trace:traces){
			logger.info(trace.toString());
		}
		
	}

	@Override
	public void send(InnerTrace trace) {
		logger.info(trace.toString());

	}

}
