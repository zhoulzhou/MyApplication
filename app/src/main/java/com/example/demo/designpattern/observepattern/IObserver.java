package com.example.demo.designpattern.observepattern;

/***
 * 抽象观察者
 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
 *
 */
public interface IObserver {
    public void update(String msg);
}
