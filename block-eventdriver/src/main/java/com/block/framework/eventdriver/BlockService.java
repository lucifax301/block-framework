package com.block.framework.eventdriver;

public interface BlockService {

	<R> R process(ServiceEvent<R> event);
}
