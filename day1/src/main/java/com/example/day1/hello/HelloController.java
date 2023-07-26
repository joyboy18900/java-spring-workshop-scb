package com.example.day1.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloSpring helloSpring;

    @GetMapping("/hello")
    public HelloResponse sayHi() {
        String result = helloSpring.sayHi();
        return new HelloResponse(result);
    }
}