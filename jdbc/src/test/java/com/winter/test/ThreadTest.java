package com.winter.test;

public class ThreadTest {

    private static /*volatile*/ boolean running = true;

    public static void a(){
        System.out.println("m start");
        while (running){

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new Thread(ThreadTest::a,"t1").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = false;
    }
}
