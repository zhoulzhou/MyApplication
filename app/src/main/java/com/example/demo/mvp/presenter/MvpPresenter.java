package com.example.demo.mvp.presenter;

import com.example.demo.mvp.model.IMvpCallBack;
import com.example.demo.mvp.model.MvpModel;
import com.example.demo.mvp.view.IBaseView;
import com.example.demo.mvp.view.IMvpView;

public class MvpPresenter extends BasePresenter<IMvpView>{

    public MvpPresenter() {

    }

    public void getData(String param) {
        if(!isViewAttached()){
            return;
        }

        getView().showLoading();

        MvpModel.getNetDate(param, new IMvpCallBack() {
            @Override
            public void onSuccess(String data) {
                if (isViewAttached()) {
                    getView().showData(data);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached()) {
                    getView().showData(msg);
                }
            }

            @Override
            public void onError() {
                if (isViewAttached()) {
                    getView().showErr();
                }
            }

            @Override
            public void onComplete() {
                if (isViewAttached()) {
                    getView().hideLoading();
                }
            }
        });
    }
}
