package com.example.day1.hello;

public class HelloResponse {

    public String message;

    public HelloResponse() {
    }

    public HelloResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
