package com.example.demo.designpattern.decoratorpattern;

public class FestivalDiscountDecorator extends DiscountDecorator {

    public FestivalDiscountDecorator(BaseDiscount component) {
        super(component);
    }

    @Override
    public void discountForVIP() {
        //do nothing
    }

    @Override
    public void discountForFestival() {
        component.discount();
        //do something new
        System.out.println("FestivalDiscountDecorator---扩展节假日折扣业务功能");
    }

    @Override
    public void discount() {
        component.discount();
    }
}
