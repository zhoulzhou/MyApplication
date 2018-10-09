package com.example.demo.mvp.presenter;

import com.example.demo.mvp.view.IBaseView;

public class BasePresenter<V extends IBaseView> {
    private V mView;

    public void attachView(V view) {
        this.mView = view;
    }

    public void detachView() {
        this.mView = null;
    }

    public boolean isViewAttached() {
        return this.mView != null;
    }

    public V getView(){
        return mView;
    }
}
