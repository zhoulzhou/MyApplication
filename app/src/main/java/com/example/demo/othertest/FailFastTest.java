package com.example.demo.othertest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FailFastTest {
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args){
        new ThreadOne().start();
        new ThreadTwo().start();
    }

    private static void printAll(){
        String value = "";
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            value = (String) iterator.next();
            System.out.println(value + ", ");
        }
    }


    /**
     * 向list中依次加入1，2，3，4，5，每添加一个数，就通过Printall遍历整个List
     */
    private static class ThreadOne extends Thread{
        public void run(){
            int i = 0;
            while(i<6){
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        }
    }

    private static class ThreadTwo extends Thread{
        public void run(){
            int i = 10;
            while(i<16){
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        }
    }
}
