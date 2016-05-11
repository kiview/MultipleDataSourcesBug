package com.groovycoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Example application for demonstrating
 * {@code 'o.a.tomcat.jdbc.pool.PooledConnection: Not loading a JDBC driver as driverClassName property is null.'}
 * warning when using multiple dataSources.
 *
 * @see <a href="https://github.com/spring-projects/spring-boot/issues/5909">Github spring-boot issue #5909</a>
 *
 * @author Kevin Wittek
 */
@SpringBootApplication
@EnableConfigurationProperties
public class MultipleDataSourcesBugApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate primaryJdbcTemplate;

	@Autowired
	private JdbcTemplate secondaryJdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MultipleDataSourcesBugApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		primaryJdbcTemplate.execute("CREATE TABLE first (foo VARCHAR)");
		secondaryJdbcTemplate.execute("CREATE TABLE second (bar VARCHAR )");
	}
}
