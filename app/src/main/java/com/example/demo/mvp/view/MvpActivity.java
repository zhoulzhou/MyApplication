package com.example.demo.mvp.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demo.mvp.presenter.MvpPresenter;
import com.example.demo.myapplication.R;

public class MvpActivity extends AppCompatActivity implements IMvpView{

    ProgressDialog progressDialog;
    TextView textView;
    MvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_main);

        textView = (TextView) findViewById(R.id.text);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("loading data");

        presenter = new MvpPresenter(this);
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

    @Override
    public void showLoading() {
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if(progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }

    @Override
    public void showData(String data) {
        textView.setText(data);
    }

    @Override
    public void showFailureMessage(String msg) {
        textView.setText(msg);
    }

    @Override
    public void showErrorMessage() {
        textView.setText("EXCPTION MSG");
    }
}
