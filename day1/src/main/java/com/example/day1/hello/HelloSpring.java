package com.example.day1.hello;

import org.springframework.stereotype.Component;

@Component
public class HelloSpring {

    public Integer count = 10;

    public String sayHi() {
        return "Hello Spring";
    }
}
