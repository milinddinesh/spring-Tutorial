package com.example.db_demo.RestController;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.db_demo.RestController.Resource.Greeting;
import com.example.db_demo.RestController.Resource.Test;

@RestController
public class ResourceController {
    private static final String template = "Hello %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(path = "/greet")
    public Greeting greeting(@RequestParam(name = "name",defaultValue = "world")String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping(path = "/test")
    public Test test(@RequestParam(name = "text")String text){
        return new Test(text);
    }
}
