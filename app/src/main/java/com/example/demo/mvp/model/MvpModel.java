package com.example.demo.mvp.model;


import android.os.Handler;

public class MvpModel {

    public static void getNetDate(final String param, final IMvpCallBack callBack){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (param){
                    case "normal":
                        callBack.onSuccess("get data from net SUCCESS!!!!");
                        break;
                    case "failure":
                        callBack.onFailure("get data from net FAILURE!!!!");
                        break;
                    case "error":
                        callBack.onError();
                        break;
                        default:
                            callBack.onFailure("get data from net failure DEFAULT!!!!");

                }
                callBack.onComplete();

            }
        }, 2000);
    }
}
