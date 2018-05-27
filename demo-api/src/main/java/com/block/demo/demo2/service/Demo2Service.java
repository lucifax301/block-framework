package com.block.demo.demo2.service;

import java.util.List;

import com.block.demo.demo.DemoModel;
import com.block.framework.common.model.ResultBean;

public interface Demo2Service {

	ResultBean saveData(DemoModel dm);
	
	DemoModel getData(DemoModel dm);
	
	List<DemoModel> listData(DemoModel dm);
}
