package com.example.demo.designpattern.mediatorpattern;

public abstract class Person {
    String name;
    Mediator mediator;

    Person(String name, Mediator mediator){
        this.name = name;
        this.mediator = mediator;
    }
}
