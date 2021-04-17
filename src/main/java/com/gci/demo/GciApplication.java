package com.gci.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class GciApplication implements CommandLineRunner {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(GciApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		jdbcTemplate.update("DROP TABLE IF EXISTS client;");
		jdbcTemplate.update("CREATE TABLE client (name VARCHAR, phone_number VARCHAR);");
	}

}
