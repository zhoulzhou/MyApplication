package com.example.demo.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demo.mvp.presenter.MvpPresenter;
import com.example.demo.myapplication.R;

public class MvpActivity extends BaseAppCompatActivity implements IMvpView{

    TextView textView;
    MvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_main);

        textView = (TextView) findViewById(R.id.text);

        presenter = new MvpPresenter();
        presenter.attachView(this);
    }

    @Override
    public void showData(String data) {
        textView.setText(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    public void getData(View view){
        presenter.getData("normal");
    }

    public void getDataForFailure(View view){
        presenter.getData("failure");
    }

    public void getDataForError(View view){
        presenter.getData("error");
    }

}
