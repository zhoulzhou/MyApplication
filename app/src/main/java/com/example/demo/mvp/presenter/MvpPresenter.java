package com.example.demo.mvp.presenter;

import com.example.demo.mvp.model.IMvpCallBack;
import com.example.demo.mvp.model.MvpModel;
import com.example.demo.mvp.view.IMvpView;

public class MvpPresenter {
    private IMvpView mView;

    public MvpPresenter() {

    }

    public void attachView(IMvpView view) {
        this.mView = view;
    }

    public void detachView() {
        this.mView = null;
    }

    public boolean isViewAttached() {
        return this.mView != null;
    }

    public void getData(String param) {
        mView.showLoading();

        MvpModel.getNetDate(param, new IMvpCallBack() {
            @Override
            public void onSuccess(String data) {
                if (isViewAttached()) {
                    mView.showData(data);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached()) {
                    mView.showFailureMessage(msg);
                }
            }

            @Override
            public void onError() {
                if (isViewAttached()) {
                    mView.showErrorMessage();
                }
            }

            @Override
            public void onComplete() {
                if (isViewAttached()) {
                    mView.hideLoading();
                }
            }
        });
    }
}
