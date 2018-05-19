package com.block.demo.demo.mapper;

import java.util.List;

import com.block.demo.demo.DemoModel;

public interface DemoMapper {

	public void addMarket(DemoModel market);
	
	public List<DemoModel> listMarket(DemoModel param);
	
	public DemoModel getMarket(DemoModel market);
	
	
	
	
}
