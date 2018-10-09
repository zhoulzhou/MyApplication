package com.example.demo.mvp.model;

import android.os.Handler;

public class UserDataModel extends BaseModel<String>{
    @Override
    public void execute(final IMvpCallBack callback) {

        // 模拟网络请求耗时操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mParams 是从父类得到的请求参数
                switch (mParams){
                    case "normal":
                        callback.onSuccess("get data from net SUCCESS!!!!");
                        break;
                    case "failure":
                        callback.onFailure("get data from net FAILURE!!!!");
                        break;
                    case "error":
                        callback.onError();
                        break;

                }
                callback.onComplete();

            }
        }, 2000);
    }
}
