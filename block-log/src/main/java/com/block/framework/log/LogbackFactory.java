package com.block.framework.log;

import java.io.ByteArrayInputStream;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class LogbackFactory {

	/**
	 * @todo
	 */
	private void init(){
		String config = null;
		LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
		JoranConfigurator jc = new JoranConfigurator();
		jc.setContext(loggerContext);
		loggerContext.reset();
		try {
			jc.doConfigure(new ByteArrayInputStream(config.getBytes()));
		} catch (JoranException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
