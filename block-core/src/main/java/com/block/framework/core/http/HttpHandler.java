package com.block.framework.core.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpHandler {

	void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
