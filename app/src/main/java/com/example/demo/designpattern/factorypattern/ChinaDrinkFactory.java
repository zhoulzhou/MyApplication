package com.example.demo.designpattern.factorypattern;

public class ChinaDrinkFactory implements IDrinkFactory {
    @Override
    public Coffee createCoffee() {
        return new Latte();
    }

    @Override
    public Tea createTea() {
        return new GreenTea();
    }

    @Override
    public Soda createSoda() {
        return new Pepsicola();
    }
}
