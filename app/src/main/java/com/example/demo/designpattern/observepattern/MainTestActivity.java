package com.example.demo.designpattern.observepattern;

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
        StringBuffer sb = new StringBuffer();
        Teacher teacher = new Teacher();

        Student zhang = new Student("Zhang");
        Student wang = new Student("Wang");
        Student yang = new Student("Yang");

        teacher.registerObserver(zhang);
        teacher.registerObserver(wang);
        teacher.registerObserver(yang);

        teacher.publicInfo("JAVA 作业题目---------");
        sb.append(teacher.getPublisInfo() + "\n");
        teacher.removeObserver(wang);
        teacher.notifyObservers();
        sb.append(zhang.getPublishinfo() + "\n");
        sb.append(wang.getPublishinfo() + "\n");
        sb.append(yang.getPublishinfo() + "\n");

        teacher.publicInfo("JAVA 高级题目---------");
        sb.append(teacher.getPublisInfo() + "\n");
        teacher.notifyObservers();
        sb.append(zhang.getPublishinfo() + "\n");
        sb.append(wang.getPublishinfo() + "\n");
        sb.append(yang.getPublishinfo() + "\n");

        mTextView.setText(sb.toString());
    }
}
