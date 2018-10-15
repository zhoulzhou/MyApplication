package com.example.demo.customview.circleprogressview;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.demo.myapplication.R;

public class MainTestActivity extends AppCompatActivity {
    private int maxProgress = 10;
    private int currentProgress = 0;
    private Handler mHandler = new Handler();
    private CircleProgressView circleProgressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_progress_view_main);
        circleProgressView = (CircleProgressView) findViewById(R.id.circleprogressview);
    }

    public void startUpdate(View view) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentProgress < maxProgress) {
                    currentProgress++;
                    System.out.println("currentProgress= " + currentProgress);
                    circleProgressView.setProgress(currentProgress);
                    mHandler.postDelayed(this, 200);
                } else {
                    mHandler.removeCallbacks(this);
                }
            }
        }, 200);
    }
}
