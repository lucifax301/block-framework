package com.block.demo.demo.mapper;

import java.util.List;

import com.block.demo.demo.DemoModel;
import com.block.framework.common.dao.BlockDao;


public interface DemoMapper extends BlockDao<DemoModel> {

	public void addMarket(DemoModel market);
	
	public List<DemoModel> listMarket(DemoModel param);
	
	public DemoModel getMarket(DemoModel market);
	
	
	
	
}
