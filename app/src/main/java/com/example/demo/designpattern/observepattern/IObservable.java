package com.example.demo.designpattern.observepattern;

/***
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 *
 */
public interface IObservable {

    public void registerObserver(IObserver observer);

    public void removeObserver(IObserver observer);

    public void notifyObservers();

}
