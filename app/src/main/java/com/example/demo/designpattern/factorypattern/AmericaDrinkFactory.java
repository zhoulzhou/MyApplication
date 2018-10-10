package com.example.demo.designpattern.factorypattern;

public class AmericaDrinkFactory implements IDrinkFactory {
    @Override
    public Coffee createCoffee() {
        return new Americano();
    }

    @Override
    public Tea createTea() {
        return new RedTea();
    }

    @Override
    public Soda createSoda() {
        return new Cocacola();
    }
}
