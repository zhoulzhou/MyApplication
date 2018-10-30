package com.example.demo.common;

import android.util.Log;

public class LogUtils {
    private static boolean isShow = true;
    private static String TAG = "MainTestActivity";

    public static void d(String msg){
        if(isShow){
            Log.d(TAG,msg);
        }
    }

    public static void e(String msg){
        if(isShow){
            Log.e(TAG,msg);
        }
    }

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
