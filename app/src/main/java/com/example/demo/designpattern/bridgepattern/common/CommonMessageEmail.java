package com.example.demo.designpattern.bridgepattern.common;

public class CommonMessageEmail implements IMessage {
    @Override
    public void send(String msg) {
        System.out.println("CommonMessageEmail send");
    }
}
