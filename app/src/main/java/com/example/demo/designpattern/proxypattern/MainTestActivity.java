package com.example.demo.designpattern.proxypattern;

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
        AbstractObject proxyObject = new ProxyObject();
        proxyObject.operation();

        IUserDao userDao = new UserDao();
        //原始类型 class com.sschen.proxy.UserDao
        System.out.println(userDao.getClass());

        //给定目标对象，动态创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(userDao).getProxyInstance();
        //代理对象类型 class com.sun.proxy.$Proxy0
        System.out.println(proxy.getClass());
        proxy.save();
        proxy.insert();
}
}
