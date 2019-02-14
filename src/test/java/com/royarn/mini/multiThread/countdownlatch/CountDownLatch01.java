package com.royarn.mini.multiThread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class CountDownLatch01 {

    private final static int threadCount = 100;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i =0;i<threadCount;i++) {
            final int threadNum = i;
            service.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await(120, TimeUnit.MILLISECONDS);
        service.shutdown();
        System.out.println("结束");
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(50);
        System.out.println(threadNum);
        Thread.sleep(50);
    }
}
