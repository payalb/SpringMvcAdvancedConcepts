package com.java.util;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("dbutil")
@PropertySource("classpath:database.properties")
public class DatabaseUtil {

	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.driverName}")
	private String driverName;
	
	
	@Bean
	public BasicDataSource getDatasource() {
		BasicDataSource ds= new BasicDataSource();
		ds.setUrl(url);
		ds.setPassword(password);
		ds.setUsername(username);
		ds.setDriverClassName(driverName);
		ds.setEnableAutoCommitOnReturn(false);
	/*	ds.setMaxIdle(20);
		ds.setMaxConnLifetimeMillis(3000);
		ds.setMaxTotal(100);
		ds.setMaxWaitMillis(3000);*/
		return ds;
	}
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	
	
}
