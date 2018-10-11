package com.example.demo.designpattern.facadepattern;

/**
 * 子系统
 */
public class Disk {
    public void start(){
        System.out.println("Disk start");
    }

    public void shutdown(){
        System.out.println("Disk shutdown");
    }
}
