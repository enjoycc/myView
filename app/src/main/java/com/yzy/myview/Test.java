package com.yzy.myview;

import java.util.concurrent.ArrayBlockingQueue;

public class Test {
    public static void main(String[] args){
        final ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue<String>(3);
        new Thread(new Runnable() {
            Object syn=new Object();
            int i=0;
            @Override
            public void run() {
                while (true){
                    try{
                        arrayBlockingQueue.add(""+i);
                    }catch (Exception e){

                    }
                    System.out.println("add"+arrayBlockingQueue+i);
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

            }
        }).start();

        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            int i;
            Object syn=new Object();
            @Override
            public void run() {
                while (true){
                    try{
                        arrayBlockingQueue.remove();
                    }catch (Exception e){

                    }

                    System.out.println("rev"+arrayBlockingQueue+i);
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

            }
        }).start();
    }
}
