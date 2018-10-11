package com.example.demo.designpattern.bridgepattern.bridge;

public class MessageEmail implements IMessageImplementor {
    @Override
    public void send(String msg) {
        System.out.println("MessageEmail send --- " + msg);
    }
}
