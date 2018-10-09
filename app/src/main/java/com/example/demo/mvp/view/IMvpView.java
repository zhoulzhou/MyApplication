package com.example.demo.mvp.view;

public interface IMvpView {

    void showLoading();

    void hideLoading();

    void showData(String data);

    void showFailureMessage(String msg);

    void showErrorMessage();
}
