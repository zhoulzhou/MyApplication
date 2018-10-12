package com.example.demo.designpattern.strategypattern;

public class PrimaryMember implements MemberStrategy {
    @Override
    public double calcuPrice(double price) {
        price *= 0.20;
        System.out.println("PrimaryMember --- calcuPrice === " + price);
        return  price;
    }
}
