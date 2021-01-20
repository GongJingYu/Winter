package com.winter.test;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            System.out.println("Hello ExecutorService");
        });

        executorService.shutdown();
    }

    @Test
    public void test1(){

    }
}
