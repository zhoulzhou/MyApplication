package com.example.demo.designpattern.compositepattern;

public abstract class File {
    private String name;

    public File(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public abstract void display();
}

