package com.java.config;

import java.util.Locale;
import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.java.exception.CustomTimeoutInterceptor;
import com.java.util.DatabaseUtil;
import com.java.util.MyInterceptor;

@Import({ DatabaseUtil.class, FlywayConfig.class , SpringExceptionConfig.class})
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.java")
public class SpringConfig implements WebMvcConfigurer {
	/*
	 * @Enablewebmvc Add support for mvc project: register controller, mappings,
	 * converters, validation support, message converters, exception handling
	 */
	
	 @Bean
     public AsyncTaskExecutor getAsyncExecutor() {
         ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
         executor.setCorePoolSize(7);
         executor.setMaxPoolSize(42);
         executor.setQueueCapacity(11);
         executor.setThreadNamePrefix("MyExecutor-");
         executor.initialize();
         return executor;
     }

	@Bean("localeChangeInterceptor")
	public LocaleChangeInterceptor getLocaleChangeInterceptor() {
		LocaleChangeInterceptor interceptor= new LocaleChangeInterceptor();
		interceptor.setParamName("language");
		return interceptor;
	}
	
	@Bean("localeResolver")
	public LocaleResolver getLocaleResolver() {
		SessionLocaleResolver resolver= new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}
	
	@Bean("messageSource")
	public ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource src= new ResourceBundleMessageSource();
		src.addBasenames("login");
		src.setDefaultEncoding("UTF-8");
		return src;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor()).excludePathPatterns("*.do");
		registry.addInterceptor(getLocaleChangeInterceptor());
	}

	@Bean("ps")
	public static PropertySourcesPlaceholderConfigurer getConfigurer() {
		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
		// cfg.setLocation(new ClassPathResource("database.properties"));
		return cfg;
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(30000);
		configurer.registerCallableInterceptors(new CustomTimeoutInterceptor());
		configurer.setTaskExecutor(getAsyncExecutor());
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/add", "/addStudent");
		registry.addViewController("/displayStudent").setViewName("displayStudent");
		//registry.addViewController("/addStudents").setViewName("addStudent");
		/* registry.addViewController("/error").setViewName("error"); */
	}

}
