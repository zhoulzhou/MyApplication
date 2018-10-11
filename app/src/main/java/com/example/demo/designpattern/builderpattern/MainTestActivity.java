package com.example.demo.designpattern.builderpattern;

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
        Person person = new Person.Builder(1,"zhang")
                .address("address  xxxx")
                .age(233)
                .phone("23455689098")
                .des("des xxxx")
                .build();

        System.out.println(person.toString());
    }
}
