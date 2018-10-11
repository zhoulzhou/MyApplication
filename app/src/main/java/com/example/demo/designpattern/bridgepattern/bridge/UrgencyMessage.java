package com.example.demo.designpattern.bridgepattern.bridge;

public class UrgencyMessage extends AbstractMessage {
    public UrgencyMessage(IMessageImplementor messageImplementor) {
        super(messageImplementor);
    }

    @Override
    public void sendMessage(String msg) {
        msg = "加急：" + msg;
        super.sendMessage(msg);
    }

    public Object watch(String msg){
        return null;
    }
}
