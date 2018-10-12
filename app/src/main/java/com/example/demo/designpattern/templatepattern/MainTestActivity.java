package com.example.demo.designpattern.templatepattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.demo.myapplication.R;

public class MainTestActivity extends AppCompatActivity{
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);

        System.out.println("--- Client test start ---");
        test();

    }

    private void test(){
        Game football = new FootballGame();
        football.play();

        Game basketball = new BasketballGame();
        basketball.play();
    }
}
