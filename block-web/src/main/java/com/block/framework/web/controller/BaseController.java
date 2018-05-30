package com.block.framework.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.block.framework.common.constant.CommonConstans;
import com.block.framework.common.model.ResultBean;
import com.block.framework.common.util.GsonUtil;
import com.github.pagehelper.PageInfo;

public class BaseController {

	/**
	 * 统计集中处理exception
	 * @param request
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler({RuntimeException.class,Exception.class})  
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {  
		//ex.printStackTrace();
		System.out.println("exceptionHandler");
        ResultBean rb = new ResultBean(CommonConstans.DEFAULT_ERROR_CODE,CommonConstans.DEFAULT_ERROR_MSG+":"+ex.getMessage());
    	printJson(response, rb,ex);
    }
	
	protected void printJson(HttpServletResponse response,ResultBean rb,Exception ex) {
		response.setContentType("application/json"); 
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;

		if(ex!=null){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			rb.setStack(sw.toString());
		}
		try {
			out = response.getWriter();
			out.print(GsonUtil.serialNulls(rb));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
	}
	
	@InitBinder    
	public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	} 
	
	public <T> ResultBean buildListResult(List<T> list){
		ResultBean rb = new ResultBean();
		rb.setResult(new PageInfo<T>(list));
		return rb;
	}
	
	public <T> ResultBean buildResult(T obj){
		ResultBean rb = new ResultBean();
		rb.setResult(obj);
		return rb;
	}
	
	public ResultBean buildSuccess(){
		ResultBean rb = new ResultBean();
		return rb;
	}
	
	public ResultBean buildError(int code,String msg){
		ResultBean rb = new ResultBean();
		rb.setCode(code);
		rb.setMsg(msg);
		return rb;
	}
}
