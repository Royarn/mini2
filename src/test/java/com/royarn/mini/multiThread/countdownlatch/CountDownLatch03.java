package com.royarn.mini.multiThread.countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class CountDownLatch03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Result result = new Result(latch);
        FutureTask<String> futureTask = new FutureTask<>(result);
        new Thread(futureTask).start();
        if (!futureTask.isDone()) {
            try {
                System.out.println("this is running......");
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(futureTask.get());
    }
}

class Result implements Callable {

    private CountDownLatch latch;

    public Result(CountDownLatch latch) {
        this.latch = latch;
    }

    public String getInfo() {
        latch.countDown();
        return "message info has returned";
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        return getInfo();
    }
}