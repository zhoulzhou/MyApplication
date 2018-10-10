package com.example.demo.designpattern.decoratorpattern;

public class VIPDiscountBusiness extends BaseDiscount {

    @Override
    public void discount() {
        System.out.println("VIPDiscountBusiness---处理vip用户的折扣业务");
    }
}
