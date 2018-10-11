package com.example.demo.designpattern.proxypattern;

public class RealObject extends AbstractObject {

    @Override
    public void operation() {
        System.out.println("RealObject --- Do Something");
    }
}
