package com.example.demo.designpattern.templatepattern;

public class FootballGame extends Game {
    @Override
    protected void initializeGame() {
        System.out.println("FootballGame initializeGame");
    }

    @Override
    protected void playGame() {
        System.out.println("FootballGame playGame");
    }

    @Override
    protected void endGame() {
        System.out.println("FootballGame endGame");
    }
}
