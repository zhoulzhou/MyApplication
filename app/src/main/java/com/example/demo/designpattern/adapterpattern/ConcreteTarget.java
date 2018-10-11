package com.example.demo.designpattern.adapterpattern;

/**
 * 具体目标类，只提供普通功能
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("普通类 具有 普通功能...");
    }
}
