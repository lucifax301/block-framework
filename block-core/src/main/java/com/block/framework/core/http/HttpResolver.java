package com.block.framework.core.http;

import javax.servlet.http.HttpServletRequest;

public interface HttpResolver {

	boolean resolve(String rule,HttpServletRequest request);
}
