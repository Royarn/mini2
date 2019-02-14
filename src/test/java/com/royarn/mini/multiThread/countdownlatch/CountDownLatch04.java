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
public class CountDownLatch04 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Result2 result2 = new Result2(latch);
        FutureTask<String> futureTask = new FutureTask<>(result2);
        new Thread(futureTask).start();
        if (!futureTask.isDone()) {
            try {
                System.out.println("执行结束......");
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(futureTask.get());
    }
}

class Result2 implements Callable {

    private CountDownLatch latch;

    public Result2(CountDownLatch latch) {
        this.latch = latch;
    }

    public String getInfo() {
        latch.countDown();
        return "programme is running ...... ";
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(2000);
        return getInfo();
    }
}