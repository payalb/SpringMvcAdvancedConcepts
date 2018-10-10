package com.java.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component("authFilter")
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest) request;
		String name= (String) req.getSession().getAttribute("name");
		if(name == null || name.trim().equalsIgnoreCase("")) { 
			/*Take to the login page i.e. addStudent.jsp*/
			System.out.println("Filter invoked!! **");
			request.getRequestDispatcher("addStudent").forward(request, response);
		}else {
			System.out.println("Filter invoked!! *");
			chain.doFilter(request, response);
		}
		
	}

}
