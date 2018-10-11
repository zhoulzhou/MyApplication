package com.example.demo.designpattern.bridgepattern;

public class RedefineAbstraction extends Abstraction {

    public RedefineAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operate() {
        //do something
        super.operate();
    }

    /**
     * 其他的操作方法
     */
    public void otherOperation() {

    }
}
