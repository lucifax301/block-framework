package com.block.framework.web.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.block.framework.common.constant.CommonConstans;
import com.block.framework.common.constant.ResultCode;
import com.block.framework.common.model.ResultBean;

public abstract class TokenFilter implements Filter {
	private Logger log = LoggerFactory.getLogger(TokenFilter.class);
	private static final boolean debug = true;
	private FilterConfig filterConfig = null;
	
	private WebApplicationContext wac;
	
	
	public TokenFilter() {
		super();
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			if (debug) {
				log("TokenFilter:Initializing filter");
			}
		}
		ServletContext servletContext = filterConfig.getServletContext();
		wac = (WebApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (debug) {
			log("TokenFilter:doFilter()");
		}

		if (request instanceof HttpServletRequest) {
			HttpServletRequest toCheckRequest = (HttpServletRequest) request;
			// 请求非法
			try {
				if (!isOkRequest(toCheckRequest)) {
					log.error("TokenFilter: url---->"+toCheckRequest.getRequestURL());
					ResultBean r = new ResultBean();
					r.setCode(ResultCode.ERRORCODE.NEEDLOGIN);
					r.setMsg(ResultCode.ERRORINFO.NEEDLOGIN);
					writeJsonObj(response, r.getResult());
					return;
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}

		Throwable problem = null;
		try {
			chain.doFilter(request, response);
		} catch (Throwable t) {
			problem = t;
			t.printStackTrace();
		}

		if (problem != null) {
			if (problem instanceof ServletException) {
				throw (ServletException) problem;
			}
			if (problem instanceof IOException) {
				throw (IOException) problem;
			}
			sendProcessingError(problem, response);
		}

	}

	private boolean isOkRequest(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paramName = (String) enu.nextElement();
			String paramValue = request.getParameter(paramName);
			params.put(paramName, paramValue);
		}
		String uri = request.getRequestURI();
		//20160310请求中凡是带有open标签的直接放行，不进行token校验。
		String m0 = "/httpaccess/open/";
		if(uri.contains(m0)){
			return true;
		}
		if(params.containsKey("open")){
			return true;
		}
		//log(params.toString());
		// 避免请求中通过pathVariable不填写userId而绕过登录校验 //后面会通过白名单过滤
		if(params.containsKey("userType")&& !params.containsKey("userId")){
			log.debug("uri--------------> "+uri);
			String m1 = "/httpaccess/v1/coaches/";
			String m2 = "/httpaccess/v1/students/";
			String userId = "";			
			if(uri.contains(m1)){
				String a = uri.substring(m1.length());
				if(a.contains("/")){
					userId = a.split("/")[0];
				}else if(a.contains("?")){
					userId = a.split("?")[0];
				}else{
					userId = a;
				}
			}else if(uri.contains(m2)){
				String a = uri.substring(m2.length());
				if(a.contains("/")){
					userId = a.split("/")[0];
				}else if(a.contains("?")){
					userId = a.split("?")[0];
				}else{
					userId = a;
				}
			}
			Pattern p = Pattern.compile("[0-9]*");
			if(userId.length() > 0 && p.matcher(userId).matches()){
				params.put("userId", userId);
			}
		}
		
		String token = request.getHeader(CommonConstans.TOKEN);
		if(token != null){
			params.put("token", token);
		}

		return requestVerify(params);
	}
	
	public abstract boolean requestVerify(Map<String, String> params) ;
	
	 

	private void log(String string) {
		filterConfig.getServletContext().log(string);

	}
	
	private void sendProcessingError(Throwable t, ServletResponse response) {
		String stackTrace = getStackTrace(t);
		if (stackTrace != null && !stackTrace.equals("")) {
			try {
				response.setContentType("text/html");
				PrintStream ps = new PrintStream(response.getOutputStream());
				PrintWriter pw = new PrintWriter(ps);
				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); // NOI18N

				// PENDING! Localize this for next official release
				pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
				pw.print(stackTrace);
				pw.print("</pre></body>\n</html>"); // NOI18N
				pw.close();
				ps.close();
				response.getOutputStream().close();
			} catch (IOException e) {
			}
		}
	}

	private String getStackTrace(Throwable t) {
		String stackTrace = null;
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.close();
			sw.close();
			stackTrace = sw.getBuffer().toString();
		} catch (Exception e) {

		}
		return stackTrace;
	}
	
	private void writeJsonObj(ServletResponse response, Object result) {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json; charset=utf-8");
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonGenerator jsonGenerator = mapper.getJsonFactory()
					.createJsonGenerator(
							(OutputStream) httpResponse.getOutputStream(),
							JsonEncoding.UTF8);
			mapper.writeValue(jsonGenerator, result);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
