package com.example.demo.mvp.presenter;

import com.example.demo.mvp.model.DataModel;
import com.example.demo.mvp.model.IMvpCallBack;
import com.example.demo.mvp.model.MvpModel;
import com.example.demo.mvp.model.Token;
import com.example.demo.mvp.model.UserDataModel;
import com.example.demo.mvp.view.IMvpView;

public class MvpPresenter extends BasePresenter<IMvpView> {

    public MvpPresenter() {

    }

    public void getData(String requestParam) {
        if (!isViewAttached()) {
            return;
        }

        getView().showLoading();

        DataModel
                // 设置请求标识token
                .request(UserDataModel.class)
                // 添加请求参数，没有则不添加
                .params(requestParam)
                // 注册监听回调
                .execute(new IMvpCallBack() {
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
