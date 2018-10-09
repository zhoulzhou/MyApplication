package com.example.demo.mvp.model;

public class DataModel {

    public static BaseModel request(String token){
        BaseModel baseModel = null;

        try {
            //利用反射机制获得对应Model对象的引用
            baseModel = (BaseModel) Class.forName(token).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return baseModel;

    }
}
