package com.example.demo.designpattern.decoratorpattern;

public abstract class DiscountDecorator extends BaseDiscount{

    public BaseDiscount component;

    public DiscountDecorator(BaseDiscount component){
        this.component = component;
    }

    public abstract void discountForVIP();
    public abstract void discountForFestival();
}
