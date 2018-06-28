package com.block.framework.pay.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.block.framework.common.constant.ResultCode;
import com.block.framework.pay.PayReqResult;
import com.block.framework.pay.PayService;
import com.block.framework.pay.PayServiceFactory;

@Controller
@RequestMapping("/open")
public class WXPayNotifyController {

	private static Logger logger = LoggerFactory.getLogger(WXPayNotifyController.class);
	
	@Autowired
	private PayServiceFactory payServiceFactory;
	
	/**
     * 第三方接口：微信回调接口
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
     * @return
     */
    @RequestMapping(value = "/paynotify/wxPayCallBack", produces = "text/html", method = RequestMethod.POST)
    public void wxPayCallBack(HttpServletRequest request, HttpServletResponse response) {
        PayReqResult r = new PayReqResult();
        try {
            response.setContentType("text/html;charset=UTF-8");

            InputStream in = request.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    "UTF-8"));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            // String crlf = System.getProperty("line.separator");
            while (tempLine != null) {
                tempStr.append(tempLine);
                // tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            String result = tempStr.toString();
            logger.info("receive:"+result);
            PayService service = payServiceFactory.getService("wx");
            r = service.payCallback(result);

            PrintWriter out = response.getWriter();
            logger.info("response to wx:"+r.getResult().get(ResultCode.RESULTKEY.DATA));
            out.print(r.getResult().get(ResultCode.RESULTKEY.DATA));
        } catch (Exception e) {
            logger.error("wxPayCallBack:" + r.getResult(), e);
            r.setCode(ResultCode.ERRORCODE.EXCEPTION);
            r.setMsgInfo(ResultCode.ERRORINFO.EXCEPTION);
        }
        
    }
}
