package com.example.demo.designpattern.commandpattern;

public class ConcreteCommand implements Command{
    private Receiver receiver;

    /**
     * 构造方法
     */
    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //通常会转调接收者对象的相应方法，让接收者来真正执行功能
        receiver.action();
    }
}
