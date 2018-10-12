package com.example.demo.designpattern.mediatorpattern;

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
        //一个房主、一个租房者、一个中介机构
        ConcreteMediator mediator = new ConcreteMediator();

        //房主和租房者只需要知道中介机构即可
        HouseOwner houseOwner = new HouseOwner("张三", mediator);
        Tenant tenant = new Tenant("李四", mediator);

        //中介结构要知道房主和租房者
        mediator.setHouseOwner(houseOwner);
        mediator.setTenant(tenant);

        System.out.println("tenant " + tenant.name + " 听说你那里有三室的房主出租.....");
        tenant.contact("听说你那里有三室的房主出租.....");

        System.out.println("houseOwner " + houseOwner.name + " 是的!请问你需要租吗?");
        houseOwner.contact("是的!请问你需要租吗?");
    }
}
