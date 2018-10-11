package com.example.demo.designpattern.bridgepattern.bridge;

public abstract class AbstractMessage {
    /**
     * 持有一个实现部分的对象
     */
    private IMessageImplementor messageImplementor;

    public AbstractMessage(IMessageImplementor messageImplementor){
        this.messageImplementor = messageImplementor;

    }

    public void sendMessage(String msg){
        System.out.println("AbstractMessage sendMessage");
        this.messageImplementor.send(msg);
    }
}
