package com.example.demo.designpattern.decoratorpattern;

public class FestivalDiscountBusiness extends BaseDiscount {
    @Override
    public void discount() {
        System.out.println("FestivalDiscountBusiness---处理节假日折扣业务");
    }
}
