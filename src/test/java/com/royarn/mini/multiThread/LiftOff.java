package com.royarn.mini.multiThread;

import java.util.concurrent.CountDownLatch;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-15
 */
public class LiftOff {

    public static void main(String[] args) throws InterruptedException {
       for (int i=0;i<5;i++) {
           new Thread(new Task01()).start();
       }
        System.out.println("everything is ready");
    }
}

class Task01 implements Runnable {

    private int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public Task01() {}

    public Task01(int countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }

    private String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!" ) + ").";
    }
}
