package com.example.demo.rxreg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.demo.common.LogUtils;
import com.example.demo.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTestActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);

        request();
    }

    private void request(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequest_interface getRequest_interface = retrofit.create(GetRequest_interface.class);

        Call<ICIBATranslation> call = getRequest_interface.getCall();

        call.enqueue(new Callback<ICIBATranslation>() {
            @Override
            public void onResponse(Call<ICIBATranslation> call, Response<ICIBATranslation> response) {
                response.body().show();
            }

            @Override
            public void onFailure(Call<ICIBATranslation> call, Throwable t) {
                LogUtils.d("call onFailure");
            }
        });

    }


}
