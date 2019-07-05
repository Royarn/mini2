package com.royarn.mini.multiThread.threadlocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-05
 */
public class Accessor implements Runnable {

    private final int id;
    public Accessor(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id +  ": " + ThreadLocalHolder.get();
    }
}

class ThreadLocalHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        private Random random = new Random(47);
        protected synchronized Integer initialValue() {
            return random.nextInt(1000);
        }
    };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0; i< 5;i++) {
            service.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        service.shutdown();
    }
}