package com.example.demo.mvp.presenter;

import com.example.demo.mvp.model.IMvpCallBack;
import com.example.demo.mvp.model.MvpModel;
import com.example.demo.mvp.view.IMvpView;

public class MvpPresenter {
    private IMvpView mView;

    public MvpPresenter(IMvpView view){
        this.mView = view;
    }

    public void getData(String param){
        mView.showLoading();

        MvpModel.getNetDate(param, new IMvpCallBack() {
            @Override
            public void onSuccess(String data) {
                mView.showData(data);
            }

            @Override
            public void onFailure(String msg) {
                mView.showFailureMessage(msg);
            }

            @Override
            public void onError() {
                mView.showErrorMessage();
            }

            @Override
            public void onComplete() {
                mView.hideLoading();
            }
        });
    }
}
