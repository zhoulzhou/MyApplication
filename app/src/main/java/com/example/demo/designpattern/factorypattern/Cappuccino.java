package com.example.demo.designpattern.factorypattern;

public class Cappuccino extends Coffee {
    @Override
    public String getCoffeeName() {
        coffeName = "卡布奇诺";
        return coffeName;
    }
}
