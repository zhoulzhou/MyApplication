package com.example.demo.designpattern.observepattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Teacher只是个名字  用来给学生发布作业
 * 代表的是Observable被被观察者就可以了
 */
public class Teacher implements IObservable {
    private List<IObserver> mObservers = new ArrayList<IObserver>();
    private String mPublisInfo;

    public void publicInfo(String info){
        this.mPublisInfo = info;
        this.notifyObservers();
    }

    @Override
    public void registerObserver(IObserver observer) {
        if(!mObservers.contains(observer)){
            mObservers.add(observer);
        }
    }

    @Override
    public void removeObserver(IObserver observer) {
        int index = mObservers.indexOf(observer);
        if(index >=0){
            mObservers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        int size = mObservers.size();
        for(int i=0; i<size; i++){
            IObserver observer = mObservers.get(i);
            observer.update(mPublisInfo);
        }
    }

    public String getPublisInfo(){
        return "Teacher 发布作业 --- " + mPublisInfo;
    }
}
