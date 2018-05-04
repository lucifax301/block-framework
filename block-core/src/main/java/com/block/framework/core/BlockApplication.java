package com.block.framework.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlockApplication {

	private static Logger log = LoggerFactory.getLogger(BlockApplication.class);
	
	private static String DEFAULT_SPING_FILE="spring-init.xml";
	
	public static void main(String[] args) {
		AbstractApplicationContext ctx = null;
		
		String file = System.getProperty("application_spring_file",DEFAULT_SPING_FILE);
		
		try {
			ctx = new ClassPathXmlApplicationContext(
					file);
			log.info("服务器启动成功***********************************");
			synchronized (ctx) {
				ctx.wait();
			}
		} catch (Exception ex) {
			log.error("服务器启动失败*********************************", ex);
			if (ctx != null) {
				ctx.destroy();
			}
			System.exit(1);
		}

	}
}
