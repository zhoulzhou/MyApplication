package com.example.demo.mvp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.demo.mvp.presenter.BasePresenter;

import java.nio.channels.AsynchronousByteChannel;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView{

    private ProgressDialog mProgressDialog;

    /**
     * 获取Presenter实例，子类实现
     */
    public abstract BasePresenter getPresenter();

    /**
     * 初始化Presenter的实例，子类实现
     */
    public abstract void initPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading data ......");

        initPresenter();
        if (getPresenter() != null){
            getPresenter().attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null){
            getPresenter().detachView();
        }
    }

    @Override
    public void showLoading() {
        if(!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if(mProgressDialog.isShowing()){
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErr() {
        showToast("showErr");
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }
}
