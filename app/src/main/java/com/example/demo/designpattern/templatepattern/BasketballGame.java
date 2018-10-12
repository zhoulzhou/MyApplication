package com.example.demo.designpattern.templatepattern;

public class BasketballGame extends Game {
    @Override
    protected void initializeGame() {
        System.out.println("BasketballGame initializeGame");
    }

    @Override
    protected void playGame() {
        System.out.println("BasketballGame playGame");
    }

    @Override
    protected void endGame() {
        System.out.println("BasketballGame endGame");
    }
}
