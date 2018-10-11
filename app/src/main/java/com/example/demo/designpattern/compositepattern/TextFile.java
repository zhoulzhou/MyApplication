package com.example.demo.designpattern.compositepattern;

public class TextFile extends File {

    public TextFile(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("--- It is a TextFile === " + getName());
    }
}
