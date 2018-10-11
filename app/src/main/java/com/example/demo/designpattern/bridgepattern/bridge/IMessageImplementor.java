package com.example.demo.designpattern.bridgepattern.bridge;

/**
 * 实现消息发送的统一接口
 */
public interface IMessageImplementor {
    void send(String msg);
}
