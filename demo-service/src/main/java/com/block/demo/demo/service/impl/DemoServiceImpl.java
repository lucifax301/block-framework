package com.block.demo.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.block.demo.demo.DemoModel;
import com.block.demo.demo.mapper.Demo2Mapper;
import com.block.demo.demo.mapper.DemoMapper;
import com.block.demo.demo.service.DemoService;
import com.block.framework.common.model.ResultBean;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoMapper demoMapper;
	
	@Autowired
	private Demo2Mapper demo2Mapper;
	
	@Override
	public ResultBean saveData(DemoModel dm) {
		//demoMapper.addMarket(dm);
		demoMapper.insert(dm);
		demo2Mapper.insert(dm);
		return new ResultBean();
	}

	@Override
	public DemoModel getData(DemoModel dm) {
		DemoModel d = demoMapper.getMarket(dm);
		return d;
	}

	@Override
	public List<DemoModel> listData(DemoModel dm) {
		return demoMapper.listMarket(dm);
		
	}

}
