package com.block.framework.log.console.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LineConverter extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		StackTraceElement[] cda = event.getCallerData();
		if(cda!=null){
			return Integer.toString(cda[0].getLineNumber());
		}else{
			return Integer.toString(CallerData.LINE_NA);
		}
		
	}

}
