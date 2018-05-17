package com.block.framework.log.console.converter;

import java.util.Iterator;
import java.util.Map;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import com.block.framework.common.constant.CommonConstans;
import com.block.framework.common.util.ThreadTruck;

public class MessageConverter extends ClassicConverter {

	@SuppressWarnings("unchecked")
	@Override
	public String convert(ILoggingEvent arg0) {
		Map<String,String> info =(Map<String,String>) ThreadTruck.get(CommonConstans.REQUEST_INFO);
		if(info!=null&&!info.isEmpty()){
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			Iterator<String> it=info.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				if(builder.length()>1) builder.append(",");
				builder.append(key).append(":").append(info.get(key));
			}
			builder.append("}");
			return builder.toString()+""+arg0.getFormattedMessage();
		}else{
			return arg0.getFormattedMessage();
		}
		
	}

}
