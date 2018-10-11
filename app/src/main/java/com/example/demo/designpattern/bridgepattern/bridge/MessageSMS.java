package com.example.demo.designpattern.bridgepattern.bridge;

public class MessageSMS implements IMessageImplementor {
    @Override
    public void send(String msg) {
        System.out.println("MessageSMS  send ---  " + msg);
    }
}
