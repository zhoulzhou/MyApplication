package com.example.demo.designpattern.bridgepattern.bridge;

public class CommonMessage extends AbstractMessage {

    public CommonMessage(IMessageImplementor messageImplementor) {
        super(messageImplementor);
    }

    @Override
    public void sendMessage(String msg) {
        //对于普通消息，直接调用父类方法，发送消息即可
        super.sendMessage(msg);
    }
}
