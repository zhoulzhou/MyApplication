package com.example.demo.designpattern.bridgepattern.common;

public class CommonMessageSMS implements IMessage {
    @Override
    public void send(String msg) {
        System.out.println("CommonMessageSMS send");
    }
}
