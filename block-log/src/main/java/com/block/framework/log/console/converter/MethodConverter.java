package com.block.framework.log.console.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class MethodConverter extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		StackTraceElement[] cda = event.getCallerData();
		if(cda!=null){
			return cda[0].getMethodName();
		}else{
			return CallerData.NA;
		}
	}

}
