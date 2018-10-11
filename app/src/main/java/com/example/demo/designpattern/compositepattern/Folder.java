package com.example.demo.designpattern.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class Folder extends File{
    private List<File>  files;

    public Folder(String name) {
        super(name);
        files = new ArrayList<File>();
    }

    public void addFile(File file){
        files.add(file);
    }

    public void removeFile(File file){
        files.remove(file);
    }

    /**
     * 浏览文件夹中的文件
     */
    @Override
    public void display() {
        System.out.println(getName() + " display ---");
        for(File file:files){
            file.display();
        }
    }
}
