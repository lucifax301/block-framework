package com.block.demo.demo.mapper;

import java.util.List;

import com.block.demo.demo.DemoModel;
import com.block.framework.core.dao.BlockDao;

public interface Demo2Mapper extends BlockDao<DemoModel> {

	public void addMarket(DemoModel market);
	
	public List<DemoModel> listMarket(DemoModel param);
	
	public DemoModel getMarket(DemoModel market);
	
	
	
	
}
