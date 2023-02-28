package com.example.db_demo.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.db_demo.storage.StorageService;
import com.example.db_demo.Models.FileInfo;

@Controller
public class UploadController {

    private final StorageService storageService;

    @Autowired
    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/viewFiles")
    public String listFiles(Model model) throws IOException {
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(UploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));
        return "uploadForm";
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
      List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
        String filename = path.getFileName().toString();
        String url = MvcUriComponentsBuilder
            .fromMethodName(UploadController.class, "serveFile", path.getFileName().toString()).build().toString();
  
        return new FileInfo(filename, url);
      }).collect(Collectors.toList());
  
      return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    //this function is called every time the generated link is clicked. 
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + file.getFilename() + "\"").body(file);
    }

    // @PostMapping("/Upload")
    // public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    //     storageService.store(file);
    //     redirectAttributes.addFlashAttribute("message","File uploaded successfully!"+file.getOriginalFilename());
    //     return "redirect:/";
    // }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        String msg = " ";
        try{
            storageService.store(file);
            msg = "File uploaded" + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }
        catch(Exception e ){
            msg = "Upload failed" + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(msg);
        }
    }
}
