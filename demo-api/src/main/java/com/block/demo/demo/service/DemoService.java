package com.block.demo.demo.service;

import java.util.List;

import com.block.demo.demo.DemoModel;
import com.block.framework.common.model.ResultBean;

public interface DemoService {

	ResultBean saveData(DemoModel dm);
	
	DemoModel getData(DemoModel dm);
	
	List<DemoModel> listData(DemoModel dm);
}
