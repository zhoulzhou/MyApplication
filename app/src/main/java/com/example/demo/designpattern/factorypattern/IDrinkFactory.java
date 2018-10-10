package com.example.demo.designpattern.factorypattern;

public interface IDrinkFactory {

    Coffee createCoffee();

    Tea createTea();

    Soda createSoda();
}
