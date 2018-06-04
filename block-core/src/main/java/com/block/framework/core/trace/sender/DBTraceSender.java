package com.block.framework.core.trace.sender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.core.trace.InnerTrace;
import com.block.framework.core.trace.TraceSender;
import com.block.framework.core.trace.mapper.TraceMapper;

public class DBTraceSender implements TraceSender {

	@Autowired
	private TraceMapper traceMapper;
	
	@Override
	public void send(List<InnerTrace> traces) {
		Map traceData = new HashMap();
		traceData.put("list", traces);
		traceMapper.batchInsert(traceData);

	}

	@Override
	public void send(InnerTrace trace) {
		traceMapper.insert(trace);
	}

}
