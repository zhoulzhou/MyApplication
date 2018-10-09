package com.example.demo.mvp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseAppCompatActivity extends AppCompatActivity implements IBaseView{

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading data ......");
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
        return BaseAppCompatActivity.this;
    }
}
