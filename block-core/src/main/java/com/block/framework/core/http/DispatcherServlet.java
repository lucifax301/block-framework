package com.block.framework.core.http;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 5766349180380479888L;
	
	private static DispatcherServlet INSTANCE;

    private static final ConcurrentHashMap<Integer, HttpHandler> handlers = new ConcurrentHashMap<Integer, HttpHandler>();

    public static void addHttpHandler(int port, HttpHandler processor) {
        handlers.put(port, processor);
    }

    public static void removeHttpHandler(int port) {
        handlers.remove(port);
    }
    
    public static DispatcherServlet getInstance() {
    	return INSTANCE;
    }
    
    public DispatcherServlet() {
    	DispatcherServlet.INSTANCE = this;
    }

    protected HttpResolver resolver = new RestHttpResolver();
    
    protected void service(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        HttpHandler handler = HttpHandlerService.resolve(request, resolver); //handlers.get(request.getLocalPort());
        if( handler == null ) {// service not found.
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Service not found.");
        } else {
            handler.handle(request, response);
        }
    }

}