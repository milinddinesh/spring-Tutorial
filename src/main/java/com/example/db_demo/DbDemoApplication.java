package com.example.db_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.Resource;

import com.example.db_demo.storage.StorageService;

@SpringBootApplication
public class DbDemoApplication {

	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(DbDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init (StorageService storageService){
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}


}
