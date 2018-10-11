package com.example.demo.designpattern.bridgepattern;

/**
 * 这就是桥
 */
public abstract class Abstraction {
    protected Implementor implementor;

    public Abstraction(Implementor implementor){
        this.implementor = implementor;
    }

    public void operate(){
        this.implementor.operate();
    }
}
