package com.example.demo.common;

public class StringUtils {

    public static boolean isEmpty(String string){
        if(string == null || string.isEmpty()){
            return true;
        }
        return false;
    }
}
