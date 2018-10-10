package com.example.demo.designpattern.decoratorpattern;

public class VIPDiscountDecorator extends DiscountDecorator {

    public VIPDiscountDecorator(BaseDiscount component) {
        super(component);
    }

    @Override
    public void discountForVIP() {
        component.discount();
        //do something new
        System.out.println("VIPDiscountDecorator---扩展Vip用户折扣业务功能");
    }

    @Override
    public void discountForFestival() {
        //do nothing
    }

    @Override
    public void discount() {
        component.discount();
    }
}
