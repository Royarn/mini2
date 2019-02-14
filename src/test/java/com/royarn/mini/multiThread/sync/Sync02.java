package com.royarn.mini.multiThread.sync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class Sync02 implements Runnable {

    private AtomicInteger integer = new AtomicInteger(0);

    public void add() {
        for (int i=0;i<1000;i++) {
            integer.incrementAndGet();
        }
    }

    public AtomicInteger getInteger() {
        return integer;
    }

    public void setInteger(AtomicInteger integer) {
        this.integer = integer;
    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) throws InterruptedException {
         Sync02 sync02 = new Sync02();
         Thread thread1 = new Thread(sync02);
         Thread thread2 = new Thread(sync02);
         thread1.start();
         thread2.start();
         thread1.join();
        thread2.join();
        System.out.println(sync02.getInteger().get());
    }
}
