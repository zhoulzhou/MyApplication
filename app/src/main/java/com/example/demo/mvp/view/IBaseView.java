package com.example.demo.mvp.view;

import android.content.Context;

public interface IBaseView {

    void showLoading();

    void hideLoading();

    void showToast(String msg);

    void showErr();

    Context getContext();
}
