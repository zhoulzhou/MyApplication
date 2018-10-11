package com.example.demo.designpattern.builderpattern;

public class Person {

    //必要参数
    private final int id ;
    private final String name;

    //可选参数
    private final int age;
    private final String address;
    private final String phone;
    private final String des;

    public Person(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
        this.phone = builder.phone;
        this.des = builder.des;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", des='" + des + '\'' +
                '}';
    }

    public static class Builder{
        private final int id;
        private final String name;

        private int age;
        private String address;
        private String phone;
        private String des;

        public Builder(int id, String name){
            this.id = id;
            this.name = name;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public Builder address(String address){
            this.address = address;
            return this;
        }

        public Builder phone(String phone){
            this.phone = phone;
            return this;
        }

        public Builder des(String des){
            this.des = des;
            return this;
        }

        public Person build(){
            return new Person(this);
        }


    }

}
