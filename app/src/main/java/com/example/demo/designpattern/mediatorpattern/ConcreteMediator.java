package com.example.demo.designpattern.mediatorpattern;

public class ConcreteMediator extends Mediator {
    //首先中介结构必须知道所有房主和租房者的信息
    private HouseOwner houseOwner;
    private Tenant tenant;

    @Override
    public void contact(String msg, Person person) {
        if(person == houseOwner){ //如果是房主，则租房者获得信息
            tenant.getInformation(msg);
        }else {                   //反正则是房主获得信息
            houseOwner.getInformation(msg);
        }
    }

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
