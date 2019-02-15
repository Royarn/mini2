package com.royarn.mini.multiThread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class ReenTranLock04 implements Runnable {

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(5000);
                System.out.println("获取锁");
            } else {
                System.out.println("获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReenTranLock04 lock04 = new ReenTranLock04();
        IntStream.range(0, 2)
                .forEach(i -> new Thread(lock04).start());
    }
}
