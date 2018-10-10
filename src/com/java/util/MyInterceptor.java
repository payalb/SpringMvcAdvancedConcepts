package com.java.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime= System.currentTimeMillis();
		response.addHeader("startTime", String.valueOf(startTime));
		System.out.println("Going to controller: " + handler);
		response.addHeader("content-type", "text/html");
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		if (modelAndView != null)
			modelAndView.addObject("headerMsg", "This is also a header!");
	}
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		long startTime=Long.parseLong(response.getHeader("startTime"));
		long endTime= System.currentTimeMillis();
		System.out.println("Time taken to serve request: "+ (endTime-startTime));
		System.out.println("Response being sent for "+ request.getRequestURI());
		if(ex!=null) {
			response.sendRedirect("/error");
		}
	}
}
