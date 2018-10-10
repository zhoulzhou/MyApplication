package com.example.demo.designpattern.factorypattern;

public class Latte extends Coffee {
    @Override
    public String getCoffeeName() {
        coffeName = "拿铁";
        return coffeName;
    }
}
