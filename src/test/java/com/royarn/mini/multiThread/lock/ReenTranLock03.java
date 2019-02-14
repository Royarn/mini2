package com.royarn.mini.multiThread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class ReenTranLock03 {

    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void add() {
        if (lock.tryLock()) {
            try {
                for (;;) {
                    count++;
                }
            } finally {
                lock.unlock();
            }
        }
        else {
            System.out.println("没有获取到锁" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenTranLock03 lock03 = new ReenTranLock03();
        new Thread(() -> {
            Thread.currentThread().setName("******Before********");
            lock03.add();
        }).start();
        Thread.sleep(1000);
        System.out.println("休息1s后");
        new Thread(() -> {
            Thread.currentThread().setName("******After********");
            lock03.add(); }
        ).start();
        System.out.println("等待......");
        //Thread.sleep(3000);
        System.out.println(lock03.count);
    }
}
