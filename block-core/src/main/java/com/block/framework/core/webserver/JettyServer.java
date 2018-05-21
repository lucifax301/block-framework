package com.block.framework.core.webserver;

import java.lang.management.ManagementFactory;

import org.apache.log4j.Logger;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;


public class JettyServer {

	private static Logger logger = Logger.getLogger(JettyServer.class);
	
	public static void main(String args[]) throws Exception{
		String webport = getEnv("WEB_PORT",null);
		if(webport == null){
			webport = getEnv("JETTY_PORT","8080");
			System.setProperty("WEB_PORT", webport);
		}
		int port = Integer.parseInt(webport);
		String contextPath = getEnv("JETTY_CONTEXT","block");
		if(!contextPath.startsWith("/")){
			contextPath = "/"+contextPath;
		}
		String webappPath = getEnv("JETTY_WEBAPP_PATH","webapp");
		String maxThreads = getEnv("JETTY_MAXTHREADS","100");
		QueuedThreadPool threadpool = new QueuedThreadPool(Integer.parseInt(maxThreads));
		threadpool.setName("block-jetty-request");
		Server server = new Server(threadpool);
		
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(port);
		server.addConnector(connector);
		
		MBeanContainer container = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
		server.addBean(container);
		
		WebAppContext context = new WebAppContext();
		context.setContextPath(contextPath);
		context.setDescriptor(webappPath+"/WEB-INF/web");
		context.setResourceBase(webappPath+"/");
		context.setClassLoader(JettyServer.class.getClassLoader());
		context.setParentLoaderPriority(true);
		
		context.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
	    
	    server.setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize", 2000000000);
		
		//context.addServlet(new ServletHolder(), "");
		
		ContextHandlerCollection handler = new ContextHandlerCollection();
		handler.addHandler(context);
		server.setHandler(handler);
		
		try{
			server.start();
		}catch(Exception ex){
			logger.error(ex);
			System.exit(-1);
		}
		server.join();
	}
	
	private static String getEnv(String key,String defaultValue){
		String value = System.getProperty(key);
		if(value!=null)
			return value;
		value = System.getenv(key);
		if(value!=null)
			return value;
		return defaultValue;
	}
}
