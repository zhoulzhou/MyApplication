package com.example.demo.designpattern.strategypattern;

public class AdvancedMember implements MemberStrategy {
    @Override
    public double calcuPrice(double price) {
        price *= 0.40;
        System.out.println("AdvancedMember --- calcuPrice === " + price);
        return  price;
    }
}
