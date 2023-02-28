package com.example.db_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.example.db_demo.storage.StorageServiceImpl;

import jakarta.annotation.Resource;

import com.example.db_demo.storage.StorageService;

@SpringBootApplication
// @EnableConfigurationProperties(StorageServiceImpl.class)
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
