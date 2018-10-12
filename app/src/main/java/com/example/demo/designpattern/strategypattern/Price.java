package com.example.demo.designpattern.strategypattern;

public class Price {

    private MemberStrategy memberStrategy;

    public Price(MemberStrategy memberStrategy){
        this.memberStrategy = memberStrategy;
    }

    public double getPrice(double price){
        System.out.println("Price --- getPrice");
        return this.memberStrategy.calcuPrice(price);
    }
}
