package com.example.db_demo.controllers.RestController;

public class Resource {
    public record Greeting(long id, String content) {}
    public record Test(String content){}
}
