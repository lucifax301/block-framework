package com.block.framework.core.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.block.framework.common.util.ThreadTruck;
import com.block.framework.core.constant.CoreConstants;

/**
 * 多数据源
 * @author devil
 *
 */
public class MultiDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		String key = (String)ThreadTruck.get(CoreConstants.ROUTE_DB);
		return (key!=null)?key:"default";   
	}

}
