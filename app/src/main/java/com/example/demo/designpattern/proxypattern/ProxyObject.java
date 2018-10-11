package com.example.demo.designpattern.proxypattern;

public class ProxyObject extends AbstractObject {
    private RealObject realObject = new RealObject();

    @Override
    public void operation() {
        System.out.println("ProxyObject --- before do something");

        realObject.operation();;

        System.out.println("ProxyObject --- after do something");
    }
}
