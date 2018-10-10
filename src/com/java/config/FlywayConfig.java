package com.java.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
	
	@Autowired
	BasicDataSource ds;

	@Bean(initMethod="migrate", value="flyway")
	public Flyway getFlyway() {
		Flyway flyway= new Flyway();
		flyway.setBaselineOnMigrate(true);
		flyway.setLocations("classpath:/migration");
		flyway.setDataSource(ds);
		return flyway;
	}
}
