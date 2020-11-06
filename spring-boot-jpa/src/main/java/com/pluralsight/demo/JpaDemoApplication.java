package com.pluralsight.demo;

import com.pluralsight.demo.database.PersonJpaRepository;
import com.pluralsight.demo.jdbc.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJpaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("Find a User with id 10001 -> {}", repository.findById(10001));

		logger.info("Inserting a user -> {}",
				repository.insert(new Person("Tara", "Berlin", new Date())));

		logger.info("Update a user 10002 -> {}",
				repository.update(new Person(10002, "Pieter", "Utrecht", new Date())));

		repository.deleteById(10002);

		logger.info("All users with NativeQuery-> {}", repository.findAllWithNativeQuery());

		logger.info("Find a users with NativeQuery -> {}", repository.findAllWithNativeQueryId("Tara"));

	}
}
