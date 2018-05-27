package com.block.demo.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.block.demo.demo.DemoModel;
import com.block.demo.demo.service.DemoService;
import com.block.framework.common.model.ResultBean;
import com.block.framework.core.ServiceMediator;
import com.block.framework.core.proxy.ProxyPool;
import com.block.framework.web.controller.BaseController;

@Controller
@ResponseBody
@RequestMapping(value="/demo")
public class DemoController extends BaseController {

	@Autowired
	ServiceMediator serviceMediator;
	
	//新增
		@RequestMapping(value="/add")
		public ResultBean addMarketing(DemoModel activity,HttpServletRequest request){
			System.out.println("#################val:"+request.getParameter("val"));
			Object obj = ProxyPool.get(DemoService.class);
			System.out.println(obj);
			DemoService demoService = serviceMediator.getService(DemoService.class);
			return demoService.saveData(activity);
		}
		
		//列表
		@RequestMapping(value="/list")
		public ResultBean geMarketList(DemoModel activity,HttpServletRequest request) {
			DemoService demoService = serviceMediator.getService(DemoService.class);
			List<DemoModel> list= demoService.listData(activity);
			return this.<DemoModel>buildListResult(list);
		}
		
		
		
		@RequestMapping(value="/get")
		public ResultBean getMarket(DemoModel activity,HttpServletRequest request){
			ResultBean resultBean = new ResultBean();
			DemoService demoService = serviceMediator.getService(DemoService.class);
			DemoModel market= demoService.getData(activity);
			resultBean.setResult(market);
			return resultBean;
		}
}
