package com.royarn.mini.multiThread.components;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-20
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test();
    }

    /**
     * test CountDownLatch
     */
    public static void test() throws InterruptedException, ExecutionException {
        CountDownLatch latch = new CountDownLatch(1);
        Task task = new Task(latch);
        FutureTask<String> futureTask = new FutureTask<>(task);
        new Thread(futureTask).start();
        if (!futureTask.isDone()) {
            latch.await();
            System.out.println("执行完毕......");

        }
        System.out.println(futureTask.get());
        System.out.println("main thread is finished");
    }
}
class Task implements Callable<String> {
    private CountDownLatch latch;
    public Task(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public String call() throws Exception {
        return callLocal();
    }
    private String callLocal() {
        try {
            System.out.println("执行中......");
            Thread.sleep(2000);
            latch.countDown();
            return "sub thread is finished";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}