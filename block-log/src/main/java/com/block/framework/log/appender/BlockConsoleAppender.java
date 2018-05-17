package com.block.framework.log.appender;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.core.ConsoleAppender;

import com.block.framework.log.console.converter.LineConverter;
import com.block.framework.log.console.converter.MethodConverter;

public class BlockConsoleAppender<E> extends ConsoleAppender<E> {

	static{
		PatternLayout.defaultConverterMap.put("m", MessageConverter.class.getName());
		PatternLayout.defaultConverterMap.put("message", MessageConverter.class.getName());
		PatternLayout.defaultConverterMap.put("msg", MessageConverter.class.getName());
		
		PatternLayout.defaultConverterMap.put("L", LineConverter.class.getName());
		PatternLayout.defaultConverterMap.put("line", LineConverter.class.getName());
		
		PatternLayout.defaultConverterMap.put("M", MethodConverter.class.getName());
		PatternLayout.defaultConverterMap.put("method", MethodConverter.class.getName());
	}
}
