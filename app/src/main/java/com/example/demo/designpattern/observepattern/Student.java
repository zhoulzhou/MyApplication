package com.example.demo.designpattern.observepattern;

public class Student implements IObserver {
    private String mName;
    private String mInfo;

    public Student(String name){
        this.mName = name;
    }

    @Override
    public void update(String msg) {
        this.mInfo = msg;
        System.out.println(mName + "得到作业:" + msg);
    }

    public String getPublishinfo(){
        return  (mName +" ++++++ " + mInfo);
    }
}
