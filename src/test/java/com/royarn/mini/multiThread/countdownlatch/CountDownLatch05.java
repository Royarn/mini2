package com.royarn.mini.multiThread.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-20
 */
public class CountDownLatch05 {

    public static void main(String[] args) {
        latch();
    }

    /**
     * CountDownLatch
     */
    public static void latch() {
        final int size = 100;
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(size);
        for (int i=0;i<10;i++)
            service.execute(new Waiting(latch));
        for (int i=0;i<10;i++)
            service.execute(new TaskPortion(latch));
        System.out.println("Launched all tasks");
        service.shutdown();
    }
}
class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch latch;
    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
        System.out.println("this task is finished");
    }
    @Override
    public String toString() {
        return String.format("%1$-3d", id);
    }
}
class Waiting implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;
    public Waiting(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return String.format("%1$-3d", id);
    }
}