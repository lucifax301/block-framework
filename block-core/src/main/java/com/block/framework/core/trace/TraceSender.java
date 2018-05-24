package com.block.framework.core.trace;

import java.util.List;

public interface TraceSender {

	void send(List<InnerTrace> traces);
	
	void send(InnerTrace trace);
}
