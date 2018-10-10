package com.java.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.java.util.AuthenticationFilter;

//Don't use @Configuration
//Equivalent of web.xml

public class WebConfig implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx= new AnnotationConfigWebApplicationContext();
		ctx.register(new Class[] {SpringConfig.class});
		ctx.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(ctx));
		
		Dynamic servletOne=servletContext.addServlet("myServlet", new DispatcherServlet());
		servletOne.addMapping("/");
		servletOne.setAsyncSupported(true);
		//servletOne.setInitParameter("contextConfigLocation", arg1)
		//1st request comes in : object of servlet : init() method
		//As soon as ur application is deployed: create the objects, call the init()
		servletOne.setLoadOnStartup(1);
		
		FilterRegistration.Dynamic filter=servletContext.addFilter("authFilter", new DelegatingFilterProxy("authFilter"));
		filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
	}

}
