package com.example.demo.designpattern.decoratorpattern;

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

        test();

    }

    private void test(){
        BaseDiscount vipDiscountBusiness, festivalDiscountBusiness;
        vipDiscountBusiness = new VIPDiscountBusiness();
        festivalDiscountBusiness = new FestivalDiscountBusiness();

        DiscountDecorator vipDiscountDecorator, festivalDiscountDecorator;
        vipDiscountDecorator = new VIPDiscountDecorator(vipDiscountBusiness);
        festivalDiscountDecorator = new FestivalDiscountDecorator(festivalDiscountBusiness);

        vipDiscountDecorator.discountForVIP();
        festivalDiscountDecorator.discountForFestival();


    }
}
