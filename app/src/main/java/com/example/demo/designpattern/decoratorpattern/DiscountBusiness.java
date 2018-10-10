package com.example.demo.designpattern.decoratorpattern;

/**
 * 这样不是个好的方式
 */
public class DiscountBusiness {

    //VIP打折活动
    public void discountForVIP(){
        //do something
    }

    //节日打折活动
    public void discountForFestival(){
        //do something
    }

    //节日给的VIP打折活动
    public void festivalDiscountForVIP(){
        //do something
    }
}
