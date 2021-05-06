package com.gci.demo;

import org.slf4j.Logger;

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
						+ "id SERIAL PRIMARY KEY, "
						+ "name VARCHAR, "
						+ "phone_number VARCHAR, "
						+ "phone_number_alt VARCHAR, "
						+ "address_id INTEGER"
						+ ");");
					jdbcTemplate.update("DROP TABLE IF EXISTS address;");
					jdbcTemplate.update("CREATE TABLE address ("
						+ "id SERIAL PRIMARY KEY, "
						+ "line1 VARCHAR, "
						+ "line2 VARCHAR, "
						+ "city VARCHAR, "
						+ "state VARCHAR, "
						+ "zip_code VARCHAR"
						+ ");");
					jdbcTemplate.update("DROP TABLE IF EXISTS pet;");
					jdbcTemplate.update("CREATE TABLE pet ("
						+ "id SERIAL PRIMARY KEY, "
						+ "client_id INTEGER, "
						+ "name VARCHAR, "
						+ "breed VARCHAR, "
						+ "medical_notes VARCHAR, "
						+ "age INTEGER, "
						+ "vaccinated BOOLEAN"
						+ ");");
					jdbcTemplate.update("DROP TABLE IF EXISTS appointment;");
					jdbcTemplate.update("CREATE TABLE appointment ("
						+ "id SERIAL PRIMARY KEY, "
						+ "client_id INTEGER, "
						+ "date DATE, "
						+ "time TIME, "
						+ "description VARCHAR"
						+ ");");
			}
		}
	}

}
