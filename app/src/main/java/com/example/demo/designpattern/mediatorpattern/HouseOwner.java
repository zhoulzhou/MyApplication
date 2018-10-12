package com.example.demo.designpattern.mediatorpattern;

public class HouseOwner extends Person {

    HouseOwner(String name, Mediator mediator) {
        super(name, mediator);
    }

    /**
     * @desc 与中介者联系
     * @param msg
     * @return void
     */
    public void contact(String msg){
        mediator.contact(msg, this);
    }

    /**
     * @desc 获取信息
     * @param msg
     * @return void
     */
    public void getInformation(String msg){
        System.out.println("房主: " + name +" ,获得信息：" + msg);
    }
}
