package com.example.demo.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demo.mvp.presenter.BasePresenter;
import com.example.demo.mvp.presenter.MvpPresenter;
import com.example.demo.myapplication.R;

public class MvpActivity extends BaseActivity implements IMvpView{

    TextView mTextView;
    MvpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_main);

        mTextView = (TextView) findViewById(R.id.text);
    }

    @Override
    public BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void initPresenter() {
        mPresenter = new MvpPresenter();
    }

    @Override
    public void showData(String data) {
        mTextView.setText(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void getData(View view){
        mPresenter.getData("normal");
    }

    public void getDataForFailure(View view){
        mPresenter.getData("failure");
    }

    public void getDataForError(View view){
        mPresenter.getData("error");
    }

}
