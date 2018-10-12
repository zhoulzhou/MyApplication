package com.example.demo.designpattern.flyweightpattern;

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
        String flyone = "flayone";
        String flytwo = "flytwo";
        String flythree = "flythree";

        FlyweightFactory factory = new FlyweightFactory();

        Flyweight fly = factory.factory(flyone);
        fly.operation("flyone outstate operation");

        fly = factory.factory(flytwo);
        fly.operation("flytwo outstate operation");


        fly = factory.factory(flythree);
        fly.operation("flythree outstate operation");

        fly = factory.factory(flytwo);
        fly.operation("flytwo outstate operation  second");
    }
}
