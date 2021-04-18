package com.gci.demo;

import java.util.List;
import org.slf4j.Logger;
import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class GciApplication implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(GciApplication.class);

	@Autowired
    private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(GciApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "drop":
					logger.info("DROPPING TABLES ON STARTUP");
					jdbcTemplate.update("DROP TABLE IF EXISTS client;");
					jdbcTemplate.update("CREATE TABLE client ("
						+ "name VARCHAR, "
						+ "phone_number VARCHAR"
						+ ");");
			}
		}
	}

}
