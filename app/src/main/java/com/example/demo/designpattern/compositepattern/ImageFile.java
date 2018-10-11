package com.example.demo.designpattern.compositepattern;

import java.util.jar.Attributes;

public class ImageFile extends File {

    public ImageFile(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("--- It is a ImageFile === " + getName());
    }
}
