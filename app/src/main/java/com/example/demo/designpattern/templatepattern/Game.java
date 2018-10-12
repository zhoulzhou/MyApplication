package com.example.demo.designpattern.templatepattern;

public abstract class Game {

    protected abstract void initializeGame();

    protected abstract void playGame();

    protected abstract void endGame();
    //以上3个为子类方法，每个不同的Game自己实现


    //模板方法  定义为final 客户端不可修改
    public final void play(){
        System.out.println("Game play --- ");

        initializeGame();

        playGame();

        endGame();
    }
}
