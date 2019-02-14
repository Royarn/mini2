package com.royarn.mini.multiThread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class ReenTranLock01 {

    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void add() {
        try {
            lock.lock();
            for (int j=0;j<100;j++) {
                count++;
            }
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenTranLock01 lock01 = new ReenTranLock01();
        new Thread(() -> lock01.add()).start();
        new Thread(() -> lock01.add()).start();
        new Thread(() -> lock01.add()).start();
        Thread.sleep(1000);
        System.out.println(lock01.count);
    }
}