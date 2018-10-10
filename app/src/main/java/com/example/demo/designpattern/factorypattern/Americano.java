package com.example.demo.designpattern.factorypattern;

public class Americano extends Coffee {

    @Override
    public String getCoffeeName() {
        coffeName = "美式咖啡";
        return coffeName;
    }
}
