package com.example.demo.designpattern.strategypattern;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public void excuteStrategy(){
        this.strategy.strateyInterface();
    }
}
