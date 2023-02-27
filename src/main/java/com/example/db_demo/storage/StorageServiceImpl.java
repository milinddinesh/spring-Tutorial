package com.example.db_demo.storage;

import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.management.RuntimeErrorException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.db_demo.storage.StorageService;

@Service
public class StorageServiceImpl implements StorageService{
    
    private final Path root = Paths.get("uploads");

    @Override
    public void init(){
        try{
            Files.createDirectories(root);
        }
        catch(IOException e){
            throw new RuntimeException("Could not initalize folder to upload");
        }
    }

    @Override
    public void store(MultipartFile file){
        try{
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        }
        catch(Exception e ){
            if (e instanceof FileAlreadyExistsException){
                throw new RuntimeException("File with that name already exist");
            }
            else {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public Stream<Path> loadAll(){
        try{
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        }
        catch(IOException e)
        {
            throw new RuntimeException("Files faild to load");
        }
    }

    @Override
    public Resource load(String filename){
        try{
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }
            else {
                throw new RuntimeException("Could not read the file ");
            }
        }
        catch(MalformedURLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteAll(){
        FileSystemUtils.deleteRecursively(root.toFile());
    }
}
    