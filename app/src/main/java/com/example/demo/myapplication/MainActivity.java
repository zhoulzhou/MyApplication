package com.example.demo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.demo.designpattern.strategypattern.Price;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test();
    }

    private void test(){
        int COUNT_BITS = Integer.SIZE - 3;
        System.out.println("COUNT_BITS= " + COUNT_BITS + " == " + Integer.toBinaryString(COUNT_BITS));

        int r = -1 << COUNT_BITS;
        System.out.println("r= " + r + " == " + Integer.toBinaryString(r));

        int s = 1 << COUNT_BITS;
        System.out.println("s= " + s + " == " + Integer.toBinaryString(s));
    }


}
