package com.royarn.mini.multiThread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class ReenTranLock02 {

    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void add() {
        try {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (;;) {
                count++;
            }
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenTranLock02 lock02 = new ReenTranLock02();
        Thread thread1 = new Thread(() -> lock02.add());
        Thread thread2 = new Thread(() -> lock02.add());
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        thread2.interrupt();
        System.out.println(lock02.count);
    }
}