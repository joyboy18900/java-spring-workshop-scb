package com.example.day1.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @EventListener
    public void send(String message){
        // TODO
        System.out.println("Call noti service :: " + message);
        System.out.println("Done");
    }

    @EventListener
    public void xxx(String message){
        // TODO
        System.out.println("Call xxx :: " + message);
        System.out.println("Done");
    }

    @EventListener
    public void sendInt(Integer message){
        // TODO
        System.out.println("Call noti int service :: " + message);
        System.out.println("Done");
    }
}