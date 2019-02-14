package com.royarn.mini.multiThread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class CountDownLatchJoin {

    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("programme is running ......");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        latch.await();
        System.out.println("everything is over");
    }
}
