package com.example.demo.common;

import android.util.Log;

public class LogUtils {
    private static boolean isShow = true;

    public static void d(String tag, String msg){
        if(isShow){
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg){
        if(isShow){
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg){
        if(isShow){
            Log.e(tag, msg);
        }
    }

}
