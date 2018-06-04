package com.block.framework.core.trace.mapper;

import java.util.List;
import java.util.Map;

import com.block.framework.common.annotation.DBRoute;
import com.block.framework.common.annotation.Dao;
import com.block.framework.core.trace.InnerTrace;

@Dao
@DBRoute("trace")
public interface TraceMapper {

	void batchInsert(Map traceData);
	
	void insert(InnerTrace model);
	
	List<InnerTrace> list(InnerTrace model);
}
