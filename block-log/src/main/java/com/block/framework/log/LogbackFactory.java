package com.block.framework.log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class LogbackFactory {

	static{
		init();
	}
	
	/**
	 * @todo
	 */
	private static void init(){
		String file = System.getProperty("LOG_BACK_FILE");
		if(file!=null){
			try {
				System.out.println("###file:"+file);
				InputStream in = LogbackFactory.class.getResourceAsStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line = null;
				StringBuilder builder =new StringBuilder();
				while((line=reader.readLine())!=null){
					builder.append(line);
				}
				reader.close();
				String config = builder.toString();
				System.out.println(config);
				LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
				JoranConfigurator jc = new JoranConfigurator();
				jc.setContext(loggerContext);
				loggerContext.reset();
			
				jc.doConfigure(new ByteArrayInputStream(config.getBytes()));
			} catch (JoranException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
