package com.example.demo.common;

public class StringUtils {

    public static boolean isEmpty(String string){
        if(string == null || string.length() <=0){
            return true;
        }
        return false;
    }
}
