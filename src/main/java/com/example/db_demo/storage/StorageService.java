package com.example.db_demo.storage;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface StorageService {

	void init();

	void store(MultipartFile file);

	Stream<Path> loadAll();

	Resource load(String filename);

	// Resource loadAsResource(String filename);

	void deleteAll();

}