package com.royarn.mini.multiThread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class CountDownLatch02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CallableImpl callable = new CallableImpl(countDownLatch);
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        if (!futureTask.isDone()) {
            try {
                System.out.println("你知道我在一直等你吗......");
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(futureTask.get());
    }
}
