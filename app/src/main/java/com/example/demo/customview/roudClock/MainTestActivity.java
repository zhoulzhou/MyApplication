package com.example.demo.customview.roudClock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.demo.myapplication.R;

public class MainTestActivity extends AppCompatActivity {
    private RoundClock roundClock;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_clock_main);
        roundClock = (RoundClock) findViewById(R.id.round_clock);
    }
}
