package com.example.demo.designpattern.facadepattern;

/**
 * 门面类（核心）
 */
public class Computer {
    private CPU mCPU;
    private Memory mMemory;
    private Disk mDisk;

    public Computer(){
        mCPU = new CPU();
        mMemory = new Memory();
        mDisk = new Disk();
    }

    public void start(){
        mCPU.start();
        mMemory.start();
        mDisk.start();
    }

    public void shutdown(){
        mCPU.shutdown();
        mMemory.shutdown();
        mDisk.shutdown();
    }
}
