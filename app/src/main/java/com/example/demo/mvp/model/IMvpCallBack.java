package com.example.demo.mvp.model;

public interface IMvpCallBack {
    void onSuccess(String data);

    void onFailure(String msg);

    void onError();

    void onComplete();
}
