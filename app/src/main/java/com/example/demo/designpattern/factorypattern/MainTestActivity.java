package com.example.demo.designpattern.factorypattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.demo.myapplication.R;

public class MainTestActivity extends AppCompatActivity{
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);

        test();

    }

    private void test(){
        String chinaDrink = getChinaDrink();
        String americDrink = getAmericaDrink();

        mTextView.setText(chinaDrink + americDrink);
    }

    private String getChinaDrink(){
        IDrinkFactory chinaFactory = new ChinaDrinkFactory();
        Coffee chinaCoffee = chinaFactory.createCoffee();
        Tea chinaTea = chinaFactory.createTea();
        Soda chinaSoda = chinaFactory.createSoda();

        StringBuilder sb = new StringBuilder();
        sb.append("中国工厂生产的饮料:");
        sb.append("\n");
        sb.append(chinaCoffee.getCoffeeName());
        sb.append("\n");
        sb.append(chinaTea.getTeaName());
        sb.append("\n");
        sb.append(chinaSoda.getSodaName());
        sb.append("\n");

        return sb.toString();
    }

    private String getAmericaDrink(){
        IDrinkFactory americFactory = new AmericaDrinkFactory();
        Coffee americCoffee = americFactory.createCoffee();
        Tea americTea = americFactory.createTea();
        Soda americSoda = americFactory.createSoda();

        StringBuffer sb = new StringBuffer();
        sb.append("美国工厂生产的饮料:");
        sb.append("\n");
        sb.append(americCoffee.getCoffeeName());
        sb.append("\n");
        sb.append(americTea.getTeaName());
        sb.append("\n");
        sb.append(americSoda.getSodaName());

        return sb.toString();
    }
}
