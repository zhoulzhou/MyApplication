package com.example.demo.designpattern.flyweightpattern;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private Map<String,Flyweight> flys = new HashMap<>();

    /**
     * 通过内蕴状态在缓存中查找对象，找不到就新建一个
     * @param intrinsicState
     * @return
     */
    public Flyweight factory(String intrinsicState){
        Flyweight fly = flys.get(intrinsicState);
        if(fly == null){
            fly = new ConcreteFlyweight(intrinsicState);
            flys.put(intrinsicState,fly);
        }
        System.out.println("FlyweightFactory factory--- " + fly + " intrinsicState--- " + intrinsicState);
        return fly;
    }
}
