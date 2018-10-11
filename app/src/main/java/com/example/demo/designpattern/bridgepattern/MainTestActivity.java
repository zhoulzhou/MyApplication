package com.example.demo.designpattern.bridgepattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.demo.designpattern.bridgepattern.bridge.AbstractMessage;
import com.example.demo.designpattern.bridgepattern.bridge.CommonMessage;
import com.example.demo.designpattern.bridgepattern.bridge.IMessageImplementor;
import com.example.demo.designpattern.bridgepattern.bridge.MessageEmail;
import com.example.demo.designpattern.bridgepattern.bridge.MessageSMS;
import com.example.demo.designpattern.bridgepattern.bridge.UrgencyMessage;
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
        IMessageImplementor messageImplementor = new MessageSMS();
        AbstractMessage abstractMessage = new CommonMessage(messageImplementor);
        abstractMessage.sendMessage("加班申请");

        messageImplementor = new MessageEmail();
        abstractMessage = new UrgencyMessage(messageImplementor);
        abstractMessage.sendMessage("加班申请速批");
    }
}
